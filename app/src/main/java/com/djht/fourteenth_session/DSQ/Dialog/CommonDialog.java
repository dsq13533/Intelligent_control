package com.djht.fourteenth_session.DSQ.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.djht.fourteenth_session.R;

public class CommonDialog extends Dialog {

    private TextView titleTv ;
    private Button negtiveBn ,positiveBn,search;
    private Spinner light_spinner;
    private TextView light_name_Edit;
    private View columnLineView ;
    private View.OnClickListener mClickListener;

    public CommonDialog(Context context) {
        super(context, R.style.CustomDialog);
    }

    public CommonDialog(Context context,View.OnClickListener clickListener) {
        super(context, R.style.CustomDialog);
        this.mClickListener = clickListener;
    }

    private String title;
    private String positive,negtive ;
    private boolean isSearch;
    private String Light_spinner,Light_name;
    private boolean isSingle = false;
    private int imageResId = -1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_dialog_d);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
        //初始化界面数据
        refreshView();
        //初始化界面控件的事件
        initEvent();
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        positiveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( onClickBottomListener!= null) {
                    Light_name=light_name_Edit.getText().toString();
                    Light_spinner=light_spinner.getSelectedItem().toString();
                    onClickBottomListener.onPositiveClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        negtiveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( onClickBottomListener!= null) {
                    onClickBottomListener.onNegtiveClick();
                }
            }
        });

    }

    /**
     * 初始化界面控件的显示数据
     */
    private void refreshView() {
        //如果用户自定了title和message
        if (!TextUtils.isEmpty(title)) {
            titleTv.setText(title);
            titleTv.setVisibility(View.VISIBLE);
        }else {
            titleTv.setVisibility(View.GONE);
        }
        //如果设置按钮的文字
        if (!TextUtils.isEmpty(positive)) {
            positiveBn.setText(positive);
        }else {
            positiveBn.setText("确定");
        }
        if (!TextUtils.isEmpty(negtive)) {
            negtiveBn.setText(negtive);
        }else {
            negtiveBn.setText("取消");
        }
        /**
         * 只显示一个按钮的时候隐藏取消按钮，回掉只执行确定的事件
         */
        if (isSingle){
            columnLineView.setVisibility(View.GONE);
            negtiveBn.setVisibility(View.GONE);
        }else {
            negtiveBn.setVisibility(View.VISIBLE);
            columnLineView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void show() {
        super.show();
        refreshView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        negtiveBn = (Button) findViewById(R.id.Common_negtive);
        positiveBn = (Button) findViewById(R.id.Common_positive);
        search = (Button) findViewById(R.id.Common_search);
        search.setOnClickListener(mClickListener);
        light_spinner=findViewById(R.id.light_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.destination, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        light_spinner.setAdapter(adapter);//把上面设置好的adapter传进spinner中（相当于弹出菜单的内容）；
        //android:entries="@array/destination"
        light_name_Edit=findViewById(R.id.light_name_Edit);
        titleTv = (TextView) findViewById(R.id.Common_title);
        columnLineView = findViewById(R.id.Common_column_line);
    }

    /**
     * 设置确定取消按钮的回调
     */
    public OnClickBottomListener onClickBottomListener;
    public CommonDialog setOnClickBottomListener(OnClickBottomListener onClickBottomListener) {
        this.onClickBottomListener = onClickBottomListener;
        return this;
    }
    public interface OnClickBottomListener{
        /**
         * 点击确定按钮事件
         */
        public void onPositiveClick();
        /**
         * 点击取消按钮事件
         */
        public void onNegtiveClick();
    }




    //...........................................................
    //以下是set和get方法
    //...........................................................

    public String getLight_spinner() {
        return Light_spinner;
    }

    public void setLight_spinner(String light_spinner) {
        Light_spinner = light_spinner;
    }

    public String getLight_name() {
        return Light_name;
    }

    public void setLight_name(String light_name) {
        Light_name = light_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public CommonDialog setSingle(boolean single) {
        isSingle = single;
        return this;
    }


    public boolean isSearch() {
        return isSearch;
    }

    public CommonDialog setSearch(boolean search) {
        isSearch = search;
        return this ;
    }
    public String getPositive() {
        return positive;
    }

    public CommonDialog setPositive(String positive) {
        this.positive = positive;
        return this ;
    }

    public String getNegtive() {
        return negtive;
    }

    public CommonDialog setNegtive(String negtive) {
        this.negtive = negtive;
        return this ;
    }
}
