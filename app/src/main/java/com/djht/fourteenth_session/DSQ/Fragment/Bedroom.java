package com.djht.fourteenth_session.DSQ.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.djht.fourteenth_session.DataBase.DBService;
import com.djht.fourteenth_session.DataBase.Version;
import com.djht.fourteenth_session.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bedroom extends Fragment {
    private ListView bedroom_ListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bedroom_d, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
            SimpleAdapter adapter=new SimpleAdapter(getContext(),
            listitem,
            R.layout.light_layout,
            new String[]{"light_number","light_name","light_state"},
            new int[]{R.id.light_number,R.id.light_name,R.id.light_state});
            bedroom_ListView.setAdapter(adapter);
    }
}
