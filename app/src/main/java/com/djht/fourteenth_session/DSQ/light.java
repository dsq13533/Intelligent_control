package com.djht.fourteenth_session.DSQ;

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
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.djht.fourteenth_session.CommonLoadPanelActivity;
import com.djht.fourteenth_session.DSQ.Dialog.CommonDialog;
import com.djht.fourteenth_session.MainActivity;
import com.djht.fourteenth_session.R;
import com.djht.fourteenth_session.Utils.Image_chage_D;

import java.io.InputStream;

public class light extends AppCompatActivity implements View.OnClickListener{

    private ImageView light_back;
    private ImageView light_add;
    //按钮声明
    private Button light_sitting_room;
    private Button light_bedroom;
    private Button light_kitchen;
    private Button light_other_zone;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_d);
        initView();
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
            Resources resources = light.this.getResources();
            Drawable drawable = resources.getDrawable(R.drawable.light_action_bar_d);
            actionBar.setBackgroundDrawable(drawable);

            ImageView light_back= (ImageView) findViewById(R.id.light_back);
            light_back.setOnClickListener(this);
            Button light_noAll=(Button)findViewById(R.id.light_noAll);
            light_noAll.setOnClickListener(this);
            ImageView light_add= (ImageView) findViewById(R.id.light_add);
            light_add.setOnClickListener(this);
            //返回图标设置
            Resources r = this.getResources();
            @SuppressLint("ResourceType") InputStream is = r.openRawResource(R.mipmap.light_back);
            BitmapDrawable bmpview = new BitmapDrawable(is);
            Bitmap bmp = Image_chage_D.createCircleImage(Image_chage_D.zoomImg(bmpview.getBitmap(),40,40));
            light_back.setImageBitmap(bmp);
            Resources r1 = this.getResources();
            //添加图标设置
            @SuppressLint("ResourceType") InputStream is1 = r1.openRawResource(R.mipmap.light_add);
            BitmapDrawable bmpview1 = new BitmapDrawable(is1);
            Bitmap bmp1 = Image_chage_D.createCircleImage(Image_chage_D.zoomImg(bmpview1.getBitmap(),40,40));
            light_add.setImageBitmap(bmp1);
        }
    }

    @Override
    public void onClick(View view) {
        Intent light_setting=new Intent(this, light_setting.class);
        final CommonDialog dialog = new CommonDialog(light.this,this);
        switch (view.getId()){
            //TODO:这里是写一键关闭和添加
            case R.id.light_back:
                this.finish();
                break;
            case R.id.light_add:
                dialog.setTitle("加入智能灯");
                dialog.setSingle(true).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        dialog.dismiss();
                        Toast.makeText(light.this,"未找到设备添加失败",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNegtiveClick() {
                        dialog.dismiss();
                    }
                }).show();
                break;
            case R.id.Common_search:
                //搜索按钮逻辑
                Intent intent=new Intent(light.this, CommonLoadPanelActivity.class);
                startActivity(intent);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(light.this,"附近未找到设备",Toast.LENGTH_SHORT).show();
                    }
                },2000);
                break;
            case R.id.light_noAll:

                break;
            case R.id.light_sitting_room:
                light_setting.putExtra("page","light_sitting_room");
                startActivity(light_setting);
                break;
            case R.id.light_bedroom:
                light_setting.putExtra("page","light_bedroom");
                startActivity(light_setting);
                break;
            case R.id.light_kitchen:
                light_setting.putExtra("page","light_kitchen");
                startActivity(light_setting);
                break;
            case R.id.light_other_zone:
                light_setting.putExtra("page","light_other_zones");
                startActivity(light_setting);
                break;
            default:
                break;
        }
    }

    private void initView() {
        light_sitting_room=findViewById(R.id.light_sitting_room);
        light_sitting_room.setOnClickListener(this);
        light_bedroom=findViewById(R.id.light_bedroom);
        light_bedroom.setOnClickListener(this);
        light_kitchen=findViewById(R.id.light_kitchen);
        light_kitchen.setOnClickListener(this);
        light_other_zone=findViewById(R.id.light_other_zone);
        light_other_zone.setOnClickListener(this);
    }
}