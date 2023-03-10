package com.djht.fourteenth_session;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.djht.fourteenth_session.DSQ.light;

public class CommonLoadPanelActivity extends AppCompatActivity {

    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_load_panel);
        //隐藏bar
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                CommonLoadPanelActivity.this.finish();
            }
        },1500);

    }
}