package com.djht.fourteenth_session.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;


import com.djht.fourteenth_session.DSQ.light;
import com.djht.fourteenth_session.HJK.air_condition_main_h;
import com.djht.fourteenth_session.R;

public class home_D extends Fragment implements View.OnClickListener{

    private ConstraintLayout main_light;
    private ConstraintLayout main_air_conditioning;
    private ConstraintLayout main_electronic_door_lock;
    private ConstraintLayout main_water_heater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_d, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initView();
        super.onActivityCreated(savedInstanceState);
    }

    private void initView(){
        main_air_conditioning = getActivity().findViewById(R.id.main_air_conditioning);
        main_air_conditioning.setOnClickListener(this);
        main_light=getActivity().findViewById(R.id.main_light);
        main_light.setOnClickListener(this);
        main_electronic_door_lock=getActivity().findViewById(R.id.main_electronic_door_lock);
        main_electronic_door_lock.setOnClickListener(this);
        main_water_heater=getActivity().findViewById(R.id.main_water_heater);
        main_water_heater.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //灯控制页面跳转
            case R.id.main_light:
                //跳转逻辑
                Intent light = new Intent(this.getActivity(), light.class);
                startActivity(light);
                break;
            case R.id.main_electronic_door_lock:
                break;
            case R.id.main_air_conditioning:
                Intent intent_air_condition = new Intent(this.getActivity(), air_condition_main_h.class);
                startActivity(intent_air_condition);
                break;
            case R.id.main_water_heater:
                break;
            case R.id.main_smart_optimization:
                break;
            case R.id.main_more_three:
                break;
            default:
                break;
        }
    }
}
