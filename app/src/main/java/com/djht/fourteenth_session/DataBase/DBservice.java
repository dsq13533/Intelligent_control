package com.djht.fourteenth_session.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.djht.fourteenth_session.DSQ.DataBase.DBService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBservice extends SQLiteOpenHelper {

    //智能灯【灯编号 | 灯名字 | 灯位置 | 开启状态】
    String LIGHT="create table light(id integer primary key autoincrement,number,name,location,state);";
    String LIADD="insert into light(number,name,location,state) values('001','主灯','卧室','0');";

    //智能门锁【锁编号 | 锁密码 | 锁状态】
    String LOCK="create table lock(id integer primary key autoincrement,number,password,state);";
    String LOADD="insert into lock(number,password,state) values('001','123456','0')";

    //智能空调【空调编号 | 空调名字 | 空调位置 | 空调温度 | 空调状态】
    String AIR="create table air(id integer primary key autoincrement,number,name,location,temperature,state);";
    String AIADD="insert into air(number,name,location,temperature,state) values('001','海尔挂墙式','客厅','20','0');";

    //智能热水器【热水器编号 | 热水器名字 | 热水器温度 | 热水器状态】
    String HEATER="create table heater(id integer primary key autoincrement,number,name,temperature,state);";
    String HEADD="insert into heater(number,name,temperature,state) values('001','一楼','20','0');";



    //创建表初始化
    public DBservice(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LIGHT);
        db.execSQL(LIADD);
        db.execSQL(LOCK);
        db.execSQL(LOADD);
        db.execSQL(AIR);
        db.execSQL(AIADD);
        db.execSQL(HEATER);
        db.execSQL(HEADD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (newVersion){
            case 1:

            default:
                break;
        }
    }
}

/**
 * 使用实例
 */
//    DBService data_operate=new DBService(context,"diary.db",null,Version.DB_Version);//最后一个参数是数据库版本
//    List<Map<String,Object>> listitem=new ArrayList<>();
//    Cursor data = data_operate.getReadableDatabase().query(DBname.ACCOUNT, null, null, null, null, null, null);
//        while(data.moveToNext())
//                {
//                Map<String,Object> map=new HashMap<>();
//        map.put("account",data.getString(1));
//        map.put("password",data.getString(2));
//        listitem.add(map);
//        }
//
//        SimpleAdapter adapter=new SimpleAdapter(getContext(),
//        listitem,
//        R.layout.account_record_data_layout,
//        new String[]{"account","password"},
//        new int[]{R.id.bat_data_account,R.id.bat_data_password});
//
//        account_record_list.setAdapter(adapter);
