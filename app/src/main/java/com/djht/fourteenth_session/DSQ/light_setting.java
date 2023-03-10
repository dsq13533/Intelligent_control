package com.djht.fourteenth_session.DSQ;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.djht.fourteenth_session.DSQ.Fragment.Bedroom;
import com.djht.fourteenth_session.DSQ.Fragment.Kitchen;
import com.djht.fourteenth_session.DSQ.Fragment.Other_zone;
import com.djht.fourteenth_session.DSQ.Fragment.Sitting_room;
import com.djht.fourteenth_session.R;

public class light_setting extends AppCompatActivity implements View.OnClickListener{

    private String pageName;
    private TextView setting_light_top_title;
    private ImageView setting_light_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_setting_d);
        //隐藏bar
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();

        initView();

    }

    private void initView() {
        setting_light_top_title=findViewById(R.id.setting_light_top_title);
        setting_light_back=findViewById(R.id.setting_light_back);
        setting_light_back.setOnClickListener(this);
        Intent intent=getIntent();
        pageName=intent.getStringExtra("page");
        //System.out.println(pageName);
        select_Fragment(pageName);
    }

    /**
     * 界面切换函数
     * @param page
     */
    private void select_Fragment(String page){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        switch (page){
            case "light_sitting_room":
                setting_light_top_title.setText("客厅");
                Sitting_room sitting_room=new Sitting_room();
                transaction.replace(R.id.light_setting_fragment,sitting_room);
                break;
            case "light_bedroom":
                setting_light_top_title.setText("卧室");
                Bedroom bedroom=new Bedroom();
                transaction.replace(R.id.light_setting_fragment,bedroom);
                break;
            case "light_kitchen":
                setting_light_top_title.setText("厨房");
                Kitchen kitchen=new Kitchen();
                transaction.replace(R.id.light_setting_fragment,kitchen);
                break;
            case "light_other_zones":
                setting_light_top_title.setText("其他");
                Other_zone other_zone=new Other_zone();
                transaction.replace(R.id.light_setting_fragment,other_zone);
                break;
            default:
                break;
        }
        transaction.commit();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.setting_light_back:
                this.finish();
                break;
            default:
                break;
        }
    }
}