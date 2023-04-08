package com.djht.fourteenth_session;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.djht.fourteenth_session.Fragment.home_D;
import com.djht.fourteenth_session.Fragment.setting_D;
import com.djht.fourteenth_session.R;

public class Main_panel_d extends AppCompatActivity implements View.OnClickListener{

    //程序退出时间控制
    private long exitTime = 0;
    private Bitmap bmp;

    private ImageView home_icon;
    private ImageView setting_icon;
    private LinearLayout main_panel_top;
    private TextView main_top_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_panel_d);

        //隐藏bar
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();

        initView();
        select_Fragment(0);
    }


    /**
     * 界面切换函数
     * @param i 切换界面值
     */
    private void select_Fragment(int i){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();

        switch (i){
            case 0:
                //getSupportActionBar().show();
                //main_panel_top.setVisibility(View.GONE);
                main_top_title.setText(R.string.app_name);
                home_D home_Fragment=new home_D();
                transaction.replace(R.id.New_main_Fragment,home_Fragment);
                home_icon.setImageResource(R.mipmap.home_block);
                break;
            case 1:
                //getSupportActionBar().hide();
                //main_panel_top.setVisibility(View.VISIBLE);
                main_top_title.setText("设置");
                setting_D setting_Fragment=new setting_D();
                transaction.replace(R.id.New_main_Fragment,setting_Fragment);
                setting_icon.setImageResource(R.mipmap.setting_block);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    /**
     * 点击切换监听函数
     * @param view
     */
    @Override
    public void onClick(View view) {
        DarkImage();
        switch (view.getId()){
            case R.id.home_icon:
                select_Fragment(0);
                break;
            case R.id.setting_icon:
                select_Fragment(1);
                break;
            default:
                break;
        }
    }


    private void DarkImage(){
        home_icon.setImageResource(R.mipmap.home_white);
        setting_icon.setImageResource(R.mipmap.setting_white);
    }

    /**
     * View初始化
     */
    private void initView() {
        home_icon=findViewById(R.id.home_icon);
        home_icon.setOnClickListener(this);
        setting_icon=findViewById(R.id.setting_icon);
        setting_icon.setOnClickListener(this);
        main_top_title=findViewById(R.id.main_top_title);
        main_top_title.setText(R.string.app_name);

        main_panel_top=findViewById(R.id.main_panel_top);
    }

}