package com.djht.fourteenth_session.ZZY.object.Fragement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.djht.fourteenth_session.R;
public class helpmen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpmen);
    }







    public void BottonBeCliecked(View v){
        //设置要跳转的页面
        Intent intent = new Intent(this, zhuActivity.class);
        //跳转
        startActivity(intent);
    }

}