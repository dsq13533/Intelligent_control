package com.djht.fourteenth_session.ZZY;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.widget.ImageView;

import com.djht.fourteenth_session.R;
import com.djht.fourteenth_session.Utils.Image_chage_D;

import java.io.InputStream;

public class kongzhiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kongzhi);
    }

    public void BottonBeCliecked(View v){
        //设置要跳转的页面
        Intent intent = new Intent(this, zhuActivity.class);
        //跳转
        startActivity(intent);
    }

    /**
     * 制作顶部导航栏
     * @param hasFocus
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().setStatusBarColor(Color.parseColor("#5DE6D6"));
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(R.layout.light_action_bar_d);
            Resources resources = kongzhiActivity.this.getResources();
            Drawable drawable = resources.getDrawable(R.drawable.light_action_bar_d);
            actionBar.setBackgroundDrawable(drawable);

            ImageView light_back= (ImageView) findViewById(R.id.light_back);
            light_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            //返回图标设置
            Resources r = this.getResources();
            @SuppressLint("ResourceType") InputStream is = r.openRawResource(R.mipmap.light_back);
            BitmapDrawable bmpview = new BitmapDrawable(is);
            Bitmap bmp = Image_chage_D.createCircleImage(Image_chage_D.zoomImg(bmpview.getBitmap(),40,40));
            light_back.setImageBitmap(bmp);
            Resources r1 = this.getResources();
        }
    }
}