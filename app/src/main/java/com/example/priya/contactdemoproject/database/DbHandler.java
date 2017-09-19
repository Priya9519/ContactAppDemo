package com.example.priya.contactdemoproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.priya.contactdemoproject.pojo.DataModel;

import java.util.ArrayList;

import static com.example.priya.contactdemoproject.Constants.StorageConstant.DATABASE_NAME;
import static com.example.priya.contactdemoproject.Constants.StorageConstant.DATABASE_VERSION;
import static com.example.priya.contactdemoproject.Constants.StorageConstant.TableData.KEY_DATETIME;
import static com.example.priya.contactdemoproject.Constants.StorageConstant.TableData.KEY_NUMBER;
import static com.example.priya.contactdemoproject.Constants.StorageConstant.TableData.TABLE_NAME;

/**
 * Created by priya on 10/8/17.
 */

public class DbHandler extends SQLiteOpenHelper {


    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_NUMBER + " TEXT," + KEY_DATETIME + " TEXT" + ")";
        Log.e("Table syntax", CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void addDataModel(DataModel dataModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NUMBER, dataModel.getNumber());
        values.put(KEY_DATETIME, dataModel.getDateTime());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public ArrayList<DataModel> getAllData() {
        ArrayList<DataModel> dataModelList= new ArrayList<DataModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DataModel dataModel = new DataModel();
                dataModel.setNumber(cursor.getString(0));
                dataModel.setDateTime(cursor.getString(1));
                dataModelList.add(dataModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dataModelList;
    }
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
}

