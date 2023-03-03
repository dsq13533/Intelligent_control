package com.djht.fourteenth_session.HJK.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.djht.fourteenth_session.DSQ.Dialog.CommonDialog;
import com.djht.fourteenth_session.HJK.airRecyclerViewAdapter;
import com.djht.fourteenth_session.HJK.itemModel_h;
import com.djht.fourteenth_session.R;

import java.util.ArrayList;

public class water_list_H extends Fragment implements View.OnClickListener,airRecyclerViewAdapter.ItemClickListener{
    RecyclerView recyclerView;
    private View view;
    private ArrayList<itemModel_h> itemList = new ArrayList<>();
    private com.djht.fourteenth_session.HJK.waterRecylerViewAdapter waterRecyclerViewAdapter;
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
        view = inflater.inflate(R.layout.water_list__h, container, false);
        waterRecyclerViewAdapter = new com.djht.fourteenth_session.HJK.waterRecylerViewAdapter(this);
        initRecyclerView();
        initData();
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
        waterRecyclerViewAdapter = new com.djht.fourteenth_session.HJK.waterRecylerViewAdapter(getActivity(),itemList);
        recyclerView.setAdapter(waterRecyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity() ,2));
    }

    /**
     * 生成模拟数据
     * 但是有bug：每次从control回退时会新增两个数据
     */
    private void initData(){
        for(int i = 0;i<2;i++){
            itemModel_h itemModel = new itemModel_h();
            itemModel.setAirName("燃气热水器");
            itemModel.setRoomName("客厅");
            itemList.add(itemModel);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.water_list_back_btn:
                getActivity().finish();
                break;
            case R.id.water_list_add_btn:
                //点击新增按钮
                final CommonDialog dialog = new CommonDialog(context);
                dialog.setTitle("新增热水设备");
                dialog.setSingle(true).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        dialog.dismiss();
                        Toast.makeText(context,dialog.getLight_name()+dialog.getLight_spinner(),Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNegtiveClick() {
                        dialog.dismiss();
                        Toast.makeText(context,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClicked(int itemId) {
        System.out.println(itemId);
        NavController navController = Navigation.findNavController(view);
        Bundle bundle = new Bundle();
        bundle.putString("num", String.valueOf(itemId));
        navController.navigate(R.id.action_water_list_H_to_water_control_H,bundle);
    }
}