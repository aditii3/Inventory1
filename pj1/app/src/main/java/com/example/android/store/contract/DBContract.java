package com.example.android.store.contract;

import android.provider.BaseColumns;


public class DBContract {
    public DBContract() {
    }

    public final static class DBEntry implements BaseColumns {
        public static final String TABLE_NAME = "store_products";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "name";
        public static final String COLUMN_PRODUCT_TYPE = "type";
        public static final String COLUMN_PRODUCT_PRICE = "price";
        public static final String COLUMN_PRODUCT_QUANTITY = "quantity";
        public static final String COLUMN_PRODUCT_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_PRODUCT_SUPPLIER_PHONE_NO = "supplier_no";

    }
}
