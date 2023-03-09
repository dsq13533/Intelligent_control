package com.djht.fourteenth_session.HJK;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.djht.fourteenth_session.R;

import java.util.ArrayList;

public class airRecyclerViewAdapter extends RecyclerView.Adapter<airRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<air_ItemModel_h> itemList;
    private static ItemClickListener mListener;
    private static int state;
    public interface ItemClickListener {
        void onItemClicked(String itemId);
    }
    public airRecyclerViewAdapter(Context context, ArrayList<air_ItemModel_h> itemList){
        this.context = context;
        this.itemList = itemList;
    }
    /**
     * 回调函数，在adpter与fragment之间进行传参
     * @param listener
     */
    public airRecyclerViewAdapter(ItemClickListener listener) {
        this.mListener = listener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.air_condition_item_h,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        air_ItemModel_h data = itemList.get(position);
        String num = data.getNumber();
        holder.airName.setText(data.getAirName());
        holder.roomName.setText(data.getRoomName());
        state = Integer.parseInt(data.getAirState());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClicked(data.getNumber());
            }
        });
        //判断是否为开机状态
        if(state == 1){
            holder.on_off_btn.setBackgroundResource(R.drawable.on_state_air_conditon_btn_h);
        }else{
            holder.on_off_btn.setBackgroundResource(R.drawable.off_state_air_conditon_btn_h);
        }
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView airName;
        private TextView roomName;
        private ImageView on_off_btn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            on_off_btn = itemView.findViewById(R.id.air_item_btn);
            airName = itemView.findViewById(R.id.air_condition_item_name);
            roomName = itemView.findViewById(R.id.air_room_name);
        }
    }
}
