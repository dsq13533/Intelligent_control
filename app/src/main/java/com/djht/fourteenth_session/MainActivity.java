package com.djht.fourteenth_session;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.djht.fourteenth_session.Utils.Get_image_D;
import com.djht.fourteenth_session.Utils.Image_url;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private ImageView Mainactivity_start;

    public static int screenWidth;
    public static int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mainactivity_start=(ImageView) findViewById(R.id.Mainactivity_start);

        //隐藏bar
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();
        //在activity中获取屏幕的尺寸
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        screenWidth = dm.widthPixels;
        //窗口高度
        screenHeight = dm.heightPixels;

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what== Image_url.CHANGE_UI){
                    Bitmap bitmap= (Bitmap) msg.obj;
                    Mainactivity_start.setImageBitmap(bitmap);
                }
            }
        };
        //获取图片
        Get_image_D.getImage(screenWidth,screenHeight,handler, Image_url.LOAD_PANEL);

        Integer time=4000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //startActivity(new Intent(LoadPanel.this,MainActivity.class));
                startActivity(new Intent(MainActivity.this, Main_panel_d.class));
                MainActivity.this.finish();
            }
        },time);

    }
}