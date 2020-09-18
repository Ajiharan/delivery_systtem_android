package com.gaya_second.android_pro.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private String num="0";

    public static final String DATABASE_NAME="BookShop.db";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onConfigure(SQLiteDatabase db){
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String DELIVERY_CREATE_ENTRIES="CREATE TABLE "+CustomerMaster.Customers.TABLE_NAME+"("+
                CustomerMaster.Customers.COLUMN_NAME_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                CustomerMaster.Customers.COLUMN_NAME_NAME+" TEXT,"+CustomerMaster.Customers.COLUMN_NAME_PHONE+
                CustomerMaster.Customers.COLUMN_NAME_MOBILE+" INTEGER,"+CustomerMaster.Customers.COLUMN_NAME_ADDRESS+
                CustomerMaster.Customers.COLUMN_NAME_DISTRICT+" TEXT,"+
                " TEXT,"+CustomerMaster.Customers.COLUMN_NAME_POSTAL +" INTEGER)";
        sqLiteDatabase.execSQL(DELIVERY_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+CustomerMaster.Customers.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}