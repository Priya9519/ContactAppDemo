package com.example.priya.contactdemoproject.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.priya.contactdemoproject.ContactFragment;
import com.example.priya.contactdemoproject.R;
import com.example.priya.contactdemoproject.listeners.ChangeFragmentListener;
import com.example.priya.contactdemoproject.pojo.DrawerModel;

import java.util.ArrayList;

/**
 * Created by priya on 19/9/17.
 */

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.MyViewHolder> {

    protected ArrayList<DrawerModel> dataList;
    private Activity context;
    private ChangeFragmentListener listener;

    //parameterised constructor of adapter which take context and arraylist as parameter
    public DrawerAdapter(ArrayList<DrawerModel> dataList, Activity context, ChangeFragmentListener listener) {
        this.dataList = dataList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_list,parent,false);
        return new MyViewHolder(itemView);
    }

    //this method is called for each item of recycelerview to set data to custom_item.xml
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final DrawerModel data = dataList.get(position);
        holder.tvTitle.setText(data.getName());
        holder.imageView.setImageResource(data.getImage());
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(position==3){
                   listener.changeFragment(new ContactFragment());
               }
           }
       });
    }
    //override method of recyclerview adapter which return size of datalist
    @Override
    public int getItemCount() {

        return (null != dataList ? dataList.size() : 0);
    }
    //initailise view of recyclerview item
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            imageView = (ImageView) view.findViewById(R.id.image_view);
        }
    }
}








