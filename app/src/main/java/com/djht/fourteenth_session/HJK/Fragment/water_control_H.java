package com.djht.fourteenth_session.HJK.Fragment;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.djht.fourteenth_session.DataBase.DBService;
import com.djht.fourteenth_session.DataBase.Version;
import com.djht.fourteenth_session.R;

public class water_control_H extends Fragment implements View.OnClickListener {

    static int on_off_state = 0;//默认未开机，以后可以传参进来初始化
    private View snow_btn;
    private View on_off_btn;
    private View heat_btn;
    private View air_improve_btn;
    private Button drop_temperature_btn;
    private Button add_temperature_btn;
    private TextView text_temperature;
    private TextView condition_name;
    private TextView degree;
    private View back_ground;
    private View back_btn;
    private Context context;
    private static String num;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.water_control__h, container, false);
        context = view.getContext();
        init(view);
        return view;
    }

    /**
     * 初始化函数
     * @param view
     */
    public void init(View view){
        //默认未开机，开机键设置为黑色
        condition_name = view.findViewById(R.id.water_control_name);
        on_off_btn = view.findViewById(R.id.on_off_btn);
        snow_btn = view.findViewById(R.id.snow_btn);
        heat_btn = view.findViewById(R.id.heat_btn);
        air_improve_btn = view.findViewById(R.id.air_improve_btn);
        drop_temperature_btn = view.findViewById(R.id.drop_temperature_btn);
        add_temperature_btn = view.findViewById(R.id.add_temperature);
        text_temperature = view.findViewById(R.id.temperature);
        back_ground = view.findViewById(R.id.back_groud);
        back_btn = view.findViewById(R.id.back_btn);
        on_off_btn.setOnClickListener(this);
        degree = view.findViewById(R.id.degree);
        add_temperature_btn.setOnClickListener(this);
        drop_temperature_btn.setOnClickListener(this);
        back_btn.setOnClickListener(this);
        GradientDrawable drawable =(GradientDrawable)on_off_btn.getBackground();
        int off_color = Color.rgb(0,0,0);
        drawable.setColor(off_color);

        degree.setVisibility(View.INVISIBLE);
        text_temperature.setVisibility(View.INVISIBLE);
        snow_btn.setVisibility(View.INVISIBLE);
        heat_btn.setVisibility(View.INVISIBLE);
        air_improve_btn.setVisibility(View.INVISIBLE);
        drop_temperature_btn.setVisibility(View.INVISIBLE);
        add_temperature_btn.setVisibility(View.INVISIBLE);
        num = getArguments().getString("num");//获得list页面的参数
        condition_name.setText("燃气热水器");
        getData(context);
    }

    /**
     * 通过list页面传送来的number进行数据库查询温度等数据
     *
     */
    @SuppressLint("Range")
    public void getData(Context context){
        DBService db =new DBService(getContext(),"diary.db",null, Version.DB_Version);//最后一个参数是数据库版本
        Cursor cursor = db.getReadableDatabase().query("heater", null,"number=?",new String[]{num},null,null,null);
        cursor.moveToFirst();
        text_temperature.setText(cursor.getString(cursor.getColumnIndex("temperature")));
        on_off_state = cursor.getInt(cursor.getColumnIndex("state"));
        cursor.close();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //灯控制页面跳转
            case R.id.drop_temperature_btn:
                //跳转逻辑
                changeTemperature(0);
                break;
            case R.id.add_temperature:
                changeTemperature(1);
                break;
            case R.id.on_off_btn:
                changeState(view);
                break;
            case R.id.back_btn:
                NavController nav = Navigation.findNavController(view);
                nav.popBackStack();
                break;
            default:
                break;
        }
    }

    /**
     *
     * @param state
     * 用来增加、减少温度示数
     * 0代表减少，1代表增加
     */
    public void changeTemperature(int state){
        int tem = Integer.parseInt((String)text_temperature.getText());
        if(state==0&&tem>10){
            tem--;
            text_temperature.setText(String.valueOf(tem));
        }else if(state==1&&tem<100){
            tem++;
            text_temperature.setText(String.valueOf(tem));
        }
    }

    /**
     * 关机与开机
     * on_off_state=0表示关机，1表示开机
     * @param view
     */
    public void changeState(View view){
        GradientDrawable drawable =(GradientDrawable)on_off_btn.getBackground();
        if(on_off_state==0){
            on_off_state = 1;
            int on_color = Color.rgb(70,157,99);
            int on_back_color = Color.rgb(223,223,225);
            drawable.setColor(on_color);
            drawable.setColor(on_color);
            back_ground.setBackgroundColor(on_back_color);
            snow_btn.setVisibility(View.VISIBLE);
            heat_btn.setVisibility(View.VISIBLE);
            air_improve_btn.setVisibility(View.VISIBLE);
            drop_temperature_btn.setVisibility(View.VISIBLE);
            add_temperature_btn.setVisibility(View.VISIBLE);
            degree.setVisibility(View.VISIBLE);
            text_temperature.setVisibility(View.VISIBLE);
        }else if(on_off_state==1){
            on_off_state = 0;
            int off_color = Color.rgb(0,0,0);
            drawable.setColor(off_color);
            int back_off_color = Color.rgb(204,207,217);
            back_ground.setBackgroundColor(back_off_color);
            snow_btn.setVisibility(View.INVISIBLE);
            degree.setVisibility(View.INVISIBLE);
            heat_btn.setVisibility(View.INVISIBLE);
            text_temperature.setVisibility(View.INVISIBLE);
            air_improve_btn.setVisibility(View.INVISIBLE);
            drop_temperature_btn.setVisibility(View.INVISIBLE);
            add_temperature_btn.setVisibility(View.INVISIBLE);
        }
    }


}