package com.djht.fourteenth_session.ZZY;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.os.Bundle;

import com.djht.fourteenth_session.DSQ.light;
import com.djht.fourteenth_session.R;
import com.djht.fourteenth_session.Utils.Image_chage_D;

import java.io.InputStream;

public class zhuActivity extends AppCompatActivity {
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu);

        // 然后的话就是添加我们的下拉列表框
        spinner = (Spinner) findViewById(R.id.spinner);
        // 在我们的这个位置的话创建我们的数组
        String[] ctype = new String[]{
                "大门","厨房","卫生间","卧室"
        };
        // 然后的话创建一个我们的一个数组适配器并且的话这个数组适配器使我们的字符串类型的
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,ctype);
        // 设置我们的数组下拉时的选项的样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 将我们的适配器和我们的下拉列表框关联起来
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn3 = (Button) findViewById(R.id.btn3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //在存放资源代码的文件夹下下，
                Intent i = new Intent(zhuActivity.this, kongzhiActivity.class);
                //启动
                startActivity(i);
                }
            }
        );

        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //在存放资源代码的文件夹下下，
                Intent i = new Intent(zhuActivity.this , helpmen.class);
                //启动
                startActivity(i);
                }
            }
        );
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
            Resources resources = zhuActivity.this.getResources();
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