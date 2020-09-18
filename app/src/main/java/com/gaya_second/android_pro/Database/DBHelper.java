package com.gaya_second.android_pro.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import com.gaya_second.android_pro.Model.Delivery;

import java.io.ByteArrayOutputStream;

public class DBHelper extends SQLiteOpenHelper {

    private String num="0";

    public static final String DATABASE_NAME="BookShop.db";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }
    @Override
    public void onConfigure(SQLiteDatabase db){
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String DELIVERY_CREATE_ENTRIES="CREATE TABLE "+CustomerMaster.Customers.TABLE_NAME+"("+
                CustomerMaster.Customers.COLUMN_NAME_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                CustomerMaster.Customers.COLUMN_NAME_NAME+" TEXT,"+
                CustomerMaster.Customers.COLUMN_NAME_PHONE+" INTEGER,"+
                CustomerMaster.Customers.COLUMN_NAME_MOBILE+" INTEGER,"+
                CustomerMaster.Customers.COLUMN_NAME_ADDRESS+" TEXT,"+
                CustomerMaster.Customers.COLUMN_NAME_DISTRICT+" TEXT,"+
                " TEXT,"+CustomerMaster.Customers.COLUMN_NAME_POSTAL +" INTEGER)";
        sqLiteDatabase.execSQL(DELIVERY_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+CustomerMaster.Customers.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean Insert_delivery_details(Delivery delivery){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(CustomerMaster.Customers.COLUMN_NAME_NAME,delivery.getContactName());
        values.put(CustomerMaster.Customers.COLUMN_NAME_MOBILE,delivery.getMobileNumber());
        values.put(CustomerMaster.Customers.COLUMN_NAME_PHONE,delivery.getPhoneNumber());
        values.put(CustomerMaster.Customers.COLUMN_NAME_ADDRESS,delivery.getAddress());
        values.put(CustomerMaster.Customers.COLUMN_NAME_DISTRICT,delivery.getDistrict());
        values.put(CustomerMaster.Customers.COLUMN_NAME_POSTAL,delivery.getPostal());

        long rowId=db.insert(CustomerMaster.Customers.TABLE_NAME,null,values);
        return rowId != -1;
    }
}