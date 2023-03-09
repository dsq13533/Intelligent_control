package com.djht.fourteenth_session.HJK;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.djht.fourteenth_session.R;

import java.util.ArrayList;

public class waterRecylerViewAdapter extends RecyclerView.Adapter<waterRecylerViewAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<water_itemModel_h> itemList;
    private static waterRecylerViewAdapter.ItemClickListener mListener;
    private int state;
    public interface ItemClickListener {
        void onItemClicked(String itemId);
    }
    public waterRecylerViewAdapter(Context context, ArrayList<water_itemModel_h> itemList){
        this.context = context;
        this.itemList = itemList;
    }
    /**
     * 回调函数，在adpter与fragment之间进行传参
     * @param listener
     */
    public waterRecylerViewAdapter(waterRecylerViewAdapter.ItemClickListener listener) {
        this.mListener = listener;
    }
    @NonNull
    @Override
    public waterRecylerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.water_item_h,parent,false);
        return new waterRecylerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull waterRecylerViewAdapter.MyViewHolder holder, int position) {
        water_itemModel_h data = itemList.get(position);
        holder.airName.setText(data.getAirName());
        holder.roomName.setText(data.getRoomName());
        state = Integer.parseInt(data.getState());//获取状态改变颜色

        //判断是否为开机状态
        if(state == 1){
            holder.air_item_btn.setBackgroundResource(R.drawable.on_state_air_conditon_btn_h);
        }else{
            holder.air_item_btn.setBackgroundResource(R.drawable.off_state_air_conditon_btn_h);
        }
        //id是数据库中的number
        String id = data.getNumber();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClicked(id);
            }
        });
        //点击开关进行背景切换
        holder.air_item_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state == 1){
                    holder.air_item_btn.setBackgroundResource(R.drawable.off_state_air_conditon_btn_h);
                    state = 0;
                }else{
                    holder.air_item_btn.setBackgroundResource(R.drawable.on_state_air_conditon_btn_h);
                    state = 1;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView airName;
        private TextView roomName;
        private ImageView air_item_btn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            airName = itemView.findViewById(R.id.air_condition_item_name);
            roomName = itemView.findViewById(R.id.air_room_name);
            air_item_btn = itemView.findViewById(R.id.air_item_btn);
        }
    }
}
