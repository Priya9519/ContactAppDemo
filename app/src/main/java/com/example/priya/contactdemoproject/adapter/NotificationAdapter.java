package com.example.priya.contactdemoproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.priya.contactdemoproject.R;
import com.example.priya.contactdemoproject.pojo.DataModel;

import java.util.ArrayList;

/**
 * Created by priya on 19/9/17.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    protected ArrayList<DataModel> dataList;
    private Context context;

    //parameterised constructor of adapter which take context and arraylist as parameter
    public NotificationAdapter(ArrayList<DataModel> dataList, Context context) {
        this.dataList = dataList;
        this.context=context;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item,parent,false);
        return new MyViewHolder(itemView);
    }
    //this method is called for each item of recycelerview to set data to custom_item.xml
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final DataModel data = dataList.get(position);
        holder.tvNumber.setText(data.getNumber());
        holder.tvDateTime.setText(data.getDateTime());
    }
    //override method of recyclerview adapter which return size of datalist
    @Override
    public int getItemCount() {

        return (null != dataList ? dataList.size() : 0);
    }
    //initailise view of recyclerview item
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNumber,tvDateTime;
        public MyViewHolder(View view) {
            super(view);
            tvNumber=(TextView)view.findViewById(R.id.tv_number);
            tvDateTime=(TextView)view.findViewById(R.id.tv_date_time);




        }
    }

}
