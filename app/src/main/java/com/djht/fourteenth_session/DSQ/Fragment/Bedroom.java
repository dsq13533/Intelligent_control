package com.djht.fourteenth_session.DSQ.Fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.djht.fourteenth_session.DataBase.DBService;
import com.djht.fourteenth_session.DataBase.Version;
import com.djht.fourteenth_session.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Bedroom extends Fragment {
    private ListView bedroom_ListView;
    private TextView light_state;
    final Handler handler=new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bedroom_d, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        initView();
    }

    private void initView() {
        bedroom_ListView=getActivity().findViewById(R.id.bedroom_ListView);

        DBService data_operate=new DBService(getContext(),"diary.db",null, Version.DB_Version);//最后一个参数是数据库版本
        List<Map<String,Object>> listitem=new ArrayList<>();
        Cursor data = data_operate.getReadableDatabase().query("light", null, null, null, null, null, null);
            while(data.moveToNext())
            {
                if(data.getString(3).equals("卧室")){
                    Map<String,Object> map=new HashMap<>();
                    map.put("light_number",data.getString(1));
                    map.put("light_name",data.getString(2));
                    if(data.getString(4).equals("0")){
                        map.put("light_state","关");
                    }else {
                        map.put("light_state","开");
                    }
                    listitem.add(map);
                }
            }

        bedroom_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                light_state=view.findViewById(R.id.light_state);
                light_state.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        String LED="0";
                        OkHttpClient client = new OkHttpClient();
                        if(light_state.getText().toString().equals("关")){
                            LED="1";
                        }else{
                            LED="0";
                        }
                        Request request = new Request.Builder()
                                .get()
                                .url("http://192.168.137.36/update?float=1&int=2&button="+LED)
                                .build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                light_state.setText("连接失败！");
                                light_state.setTextColor(Color.RED);
                            }
                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                if(response.isSuccessful()){//回调的方法执行在子线程。
                                    Log.d("kwwl","获取数据main_more_three成功了");
                                    Log.d("kwwl","response.code()=="+response.code());
                                    System.out.println(response.code());
                                    light_state.setTextColor(Color.BLACK);
                                    if(light_state.getText().toString().equals("关")){
                                        light_state.setText("开");
                                    }else{
                                        light_state.setText("关");
                                    }
                                    Log.d("kwwl","response.body().string()=="+response.body().string());
                                }
                            }
                        });
                    }
                });
            }
        });


            SimpleAdapter adapter=new SimpleAdapter(getContext(),
            listitem,
            R.layout.light_layout,
            new String[]{"light_number","light_name","light_state"},
            new int[]{R.id.light_number,R.id.light_name,R.id.light_state});
            bedroom_ListView.setAdapter(adapter);
    }
}
