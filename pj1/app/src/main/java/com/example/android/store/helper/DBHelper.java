package com.example.android.store.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.store.contract.DBContract.DBEntry;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = DBHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "store.db";
    private final String CREATE_TABLE = "CREATE TABLE " + DBEntry.TABLE_NAME + " ("
            + DBEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DBEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL,"
            + DBEntry.COLUMN_PRODUCT_TYPE + " TEXT NOT NULL,"
            + DBEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL,"
            + DBEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL,"
            + DBEntry.COLUMN_PRODUCT_SUPPLIER_NAME + " TEXT NOT NULL,"
            + DBEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NO + " INTEGER NOT NULL);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d(TAG, "Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
