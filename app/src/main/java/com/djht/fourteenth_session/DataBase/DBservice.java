package com.djht.fourteenth_session.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBservice extends SQLiteOpenHelper {

    //智能灯【灯编号 | 灯名字 | 灯位置 | 开启状态】
    String LIGHT="create table light(id integer primary key autoincrement,number,name,location,state);";
    String LIADD="insert into light(number,name,location,state) values('1945.10.1','fffff','','');";

    //智能门锁【锁编号 | 锁密码 | 锁状态】
    String LOCK="create table lock(id integer primary key autoincrement,number,password,state);";
    String LOADD="insert into lock(number,password,state) values('注册','','')";

    //智能空调【空调编号 | 空调名字 | 空调位置 | 空调温度 | 空调状态】
    String AIR="create table air(id integer primary key autoincrement,number,name,location,temperature,state);";
    String AIADD="insert into air(number,name,location,temperature,state) values('时间','','','','');";

    //智能热水器【热水器编号 | 热水器名字 | 热水器温度 | 热水器状态】
    String HEATER="create table heater(id integer primary key autoincrement,number,name,temperature,state);";
    String HEADD="insert into heater(name,data) values('switch','0'),('QQ名字','吾日三省吾身');";



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
