package com.example.priya.contactdemoproject.ui.fragments;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.priya.contactdemoproject.R;
import com.example.priya.contactdemoproject.adapter.NotificationAdapter;
import com.example.priya.contactdemoproject.pojo.DataModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by priya on 19/9/17.
 */

public class NotificationFragment  extends Fragment{
    private ArrayList<DataModel> dataModels;
    private RecyclerView recyclerView;
    private NotificationAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.notification_fragment,container,false);
        ContentResolver contentResolver = getActivity().getContentResolver();
        Cursor cursor = contentResolver.query( Uri.parse( "content://sms/inbox" ), null, null, null, null);
        cursor.moveToFirst();
        initView(view);
        dataModels=new ArrayList<>();
        int date=cursor.getColumnIndex(Telephony.Sms.Inbox.DATE);
        int number=cursor.getColumnIndex(Telephony.Sms.Inbox.ADDRESS);
        while (cursor.moveToNext()) {
            String phNumber = cursor.getString(number);
            String callDate = cursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a", Locale.getDefault());
            String DateToStr = format.format(callDayTime);
            final String[] projection = new String[] {ContactsContract.Data.DISPLAY_NAME};

            String displayName = null;
            Cursor contactCursor = null;

            try {
                Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                        Uri.encode(phNumber));

                contactCursor =getActivity().getContentResolver().query(uri,
                        projection, null, null, null);
                if (contactCursor != null && contactCursor.moveToFirst()) {
                    displayName = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            finally {
                if(contactCursor != null) {
                    contactCursor.close();
                }
            }
            if(displayName==null){
                dataModels.add(new DataModel(phNumber,DateToStr));
            }
            else{
                dataModels.add(new DataModel(displayName,DateToStr));
            }
            Log.e("Number",phNumber);
            Log.e("DateTime",DateToStr);
            Log.e("name",displayName+"");


        }
        setRecyclerView(dataModels);
        return view;
    }

    private void initView(View view) {
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
    }

    public void setRecyclerView(ArrayList<DataModel> dataList) {
        mAdapter=new NotificationAdapter(dataList,getContext());
        LinearLayoutManager layoutManager = new   LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }
}

