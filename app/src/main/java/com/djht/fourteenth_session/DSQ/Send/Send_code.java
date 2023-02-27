package com.djht.fourteenth_session.DSQ.Send;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.djht.fourteenth_session.Utils.Image_url;

import java.io.IOException;
import java.util.concurrent.Callable;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Send_code implements Callable{

    private static OkHttpClient okHttpClient;
    private Handler handler;

    //构造器私有化
    private Send_code(){}


    //TODO:这里实现数据发送
    public static String Send_message_GET(String url){
        final String[] s = {null};
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){//回调的方法执行在子线程。
                    Log.d("kwwl","获取数据成功了");
                    Log.d("kwwl","response.code()=="+response.code());
                    Log.d("kwwl","response.body().string()=="+response.body().string());
                    s[0] =response.body().toString();
                }
            }
        });
        return s[0];
    }


    public static void Send_message_Post(String url,String Code){
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("state", Code);//传递键值对参数
        Request request = new Request.Builder()//创建Request 对象。
                .url(url)
                .post(formBody.build())//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //使用Hanlder发送消息
                /*Message msg = Message.obtain();
                msg.what = Image_url.CHANGE_UI;
                msg.obj = bitmap;
                handler.sendMessage(msg);*/
            }
        });//此处省略回调方法。
    }

    private void accept(){
        String string;
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what== Image_url.CHANGE_UI){
                    String s= (String) msg.obj;
                    //string=s;
                }else if(msg.what== Image_url.ERR){
                    //Toast.makeText(NewMainActivity.this,"网络不佳！请重试！",Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    @Override
    public Object call() throws Exception {

        return null;
    }
}
