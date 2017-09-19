package com.example.priya.contactdemoproject.frontend;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.priya.contactdemoproject.R;
import com.example.priya.contactdemoproject.adapter.CustomAdapter;
import com.example.priya.contactdemoproject.database.DbHandler;
import com.example.priya.contactdemoproject.pojo.DataModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by priya on 15/9/17.
 */

public class CallLogFragment extends Fragment {
    ArrayList<DataModel> dataModelList=new ArrayList<>();
    DbHandler db;
    private RecyclerView recyclerView;
    private CustomAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.call_log_fragment,container,false);
        initView(view);
        db=new DbHandler(getContext());
        db.deleteAll();
        getCallDetails();
        //getContactList();
        return view;
    }
    private void initView(View view) {
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
    }
    private void getCallDetails() {
        StringBuffer sb = new StringBuffer();
        Cursor managedCursor = getActivity().managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int name = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        while (managedCursor.moveToNext()) {
            String phNumber = managedCursor.getString(number);
            String callerName = managedCursor.getString(name);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a", Locale.getDefault());
            String DateToStr = format.format(callDayTime);
            if (callerName == null) {
                db.addDataModel(new DataModel(phNumber,DateToStr));
            } else {
                db.addDataModel(new DataModel(callerName,DateToStr));
            }
        }
        dataModelList = db.getAllData();
        setRecyclerView(dataModelList);
    }
    public void setRecyclerView(ArrayList<DataModel> dataList) {
        mAdapter=new CustomAdapter(dataList,getContext());
        LinearLayoutManager layoutManager = new   LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }
}



