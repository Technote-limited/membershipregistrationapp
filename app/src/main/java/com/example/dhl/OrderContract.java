package com.example.dhl;

import android.provider.BaseColumns;

public class OrderContract {
    private OrderContract() {
    }
    public static final class OrderEntry implements BaseColumns {
        public static final String TABLE_NAME = "orderList";
        public static final String COLUMN_ORDER_NUMBER = "orderNumber";
        public static final String COLUMN_ORDER_PRODUCT= "orderProduct";
        public static final String COLUMN_QUANTITY = "amount";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
