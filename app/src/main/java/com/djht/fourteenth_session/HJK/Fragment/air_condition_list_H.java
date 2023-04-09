package com.djht.fourteenth_session.HJK.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.djht.fourteenth_session.CommonLoadPanelActivity;
import com.djht.fourteenth_session.DSQ.Dialog.CommonDialog;
import com.djht.fourteenth_session.DSQ.light;
import com.djht.fourteenth_session.DataBase.DBService;
import com.djht.fourteenth_session.DataBase.Version;
import com.djht.fourteenth_session.HJK.airRecyclerViewAdapter;
import com.djht.fourteenth_session.HJK.air_ItemModel_h;
import com.djht.fourteenth_session.R;

import java.util.ArrayList;


public class air_condition_list_H extends Fragment implements View.OnClickListener,airRecyclerViewAdapter.ItemClickListener{
    RecyclerView recyclerView;
    private View view;
    private ArrayList<air_ItemModel_h> itemList = new ArrayList<>();
    private Handler handler=new Handler();
    private airRecyclerViewAdapter airRecyclerViewAdapter;
    private ImageView back_btn;
    private ImageView add_btn;
    private Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.air_condition_list__h, container, false);
        airRecyclerViewAdapter = new airRecyclerViewAdapter(this);
        initRecyclerView();
        getDataList();
        initBtn();
        context = view.getContext();
        return view;
    }


    public void initBtn(){
        back_btn = view.findViewById(R.id.water_list_back_btn);
        add_btn = view.findViewById(R.id.water_list_add_btn);
        add_btn.setOnClickListener(this);
        back_btn.setOnClickListener(this);
    }

    /**
     * 初始化recyclerView
     */
    private void initRecyclerView(){
        recyclerView = view.findViewById(R.id.water_item_recylerView);
        airRecyclerViewAdapter = new airRecyclerViewAdapter(getActivity(),itemList);
        recyclerView.setAdapter(airRecyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity() ,2));
    }

    private void getDataList(){
        itemList.clear();
        DBService data_operate=new DBService(getContext(),"diary.db",null, Version.DB_Version);//最后一个参数是数据库版本
        Cursor data = data_operate.getReadableDatabase().query("air", null, null, null, null, null, null);
        int nmIdx = data.getColumnIndex("name");
        int numIdx = data.getColumnIndex("number");
        int temIdx = data.getColumnIndex("temperature");
        int staIdx = data.getColumnIndex("state");
        int latIdx = data.getColumnIndex("location");

        while(data.moveToNext())
        {
            air_ItemModel_h itemModel = new air_ItemModel_h(data.getString(nmIdx),data.getString(latIdx),data.getString(staIdx),data.getString(temIdx),data.getString(numIdx));
            itemList.add(itemModel);
        }
        data.close();
    }

    @Override
    public void onClick(View view) {
        final CommonDialog dialog = new CommonDialog(context,this);
        switch (view.getId()) {
            case R.id.water_list_back_btn:
                getActivity().finish();
                break;
            case R.id.water_list_add_btn:
                //点击新增按钮
                dialog.setTitle("新增空调设备");
                dialog.setSingle(true).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        dialog.dismiss();
                        Toast.makeText(context,"未找到设备添加失败",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNegtiveClick() {
                        dialog.dismiss();
                    }
                }).show();
                break;
            case R.id.Common_search:
                //搜索按钮逻辑
                Intent intent=new Intent(context, CommonLoadPanelActivity.class);
                startActivity(intent);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"附近未找到设备",Toast.LENGTH_SHORT).show();
                    }
                },2000);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClicked(String itemId) {
        System.out.println(itemId);
        NavController navController = Navigation.findNavController(view);
        Bundle bundle = new Bundle();
        bundle.putString("num", itemId);
        boolean off = true;
        if(off){
            Toast.makeText(getView().getContext(),"设备未连接请重试",Toast.LENGTH_SHORT).show();
        }else{
            navController.navigate(R.id.action_air_condition_list_H_to_air_condition_control_H,bundle);
        }
    }
}