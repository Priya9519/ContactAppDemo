package com.example.priya.contactdemoproject;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.priya.contactdemoproject.adapter.ContactAdapter;
import com.example.priya.contactdemoproject.pojo.ContactList;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {
    private ArrayList<ContactList> dataList=new ArrayList<>();
    private ContactAdapter mAdapter;
    private RecyclerView recyclerView;
    private String name,number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        getContactList();
    }
    private void getContactList() {
        ContentResolver cr =getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        number = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    }
                    pCur.close();
                }
                dataList.add(new ContactList(name,number));
            }
        }
        if(cur!=null){
            cur.close();
        }
        setRecyclerView(dataList);
    }
    public void setRecyclerView(ArrayList<ContactList> dataList) {
        mAdapter=new ContactAdapter(dataList,this);
        LinearLayoutManager layoutManager = new  LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

}
