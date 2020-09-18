package com.gaya_second.android_pro.Database;
import android.provider.BaseColumns;
public final class CustomerMaster {
    private CustomerMaster(){}
    public static class Customers implements BaseColumns {
        public static final String TABLE_NAME="delivery";
        public static final String  COLUMN_NAME_ID="id";
        public static final String COLUMN_NAME_NAME="contactName";
        public static final String COLUMN_NAME_MOBILE="mobile";
        public static final String COLUMN_NAME_PHONE="phone";
        public static final String COLUMN_NAME_ADDRESS="address";
        public static final String COLUMN_NAME_DISTRICT="district";
        public static final String COLUMN_NAME_POSTAL="postal";
    }


}
