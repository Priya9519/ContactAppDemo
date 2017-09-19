package com.example.priya.contactdemoproject.Constants;

import android.provider.BaseColumns;

/**
 * Created by priya on 10/8/17.
 */

public final  class StorageConstant {
    public static final int DATABASE_VERSION = 11;
    public static final String DATABASE_NAME = "infoManager";
    public StorageConstant() {
    }
    public static class TableData implements BaseColumns{
        public static final String TABLE_NAME= "callLog";
        public static final String KEY_NUMBER = "number";
        public static final String KEY_DATETIME = "dateTime";

    }
}
