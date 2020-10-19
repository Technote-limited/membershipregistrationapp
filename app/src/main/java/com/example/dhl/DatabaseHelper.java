package com.example.dhl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static  final String DATABASE_NAME = "member.db";
    public static  final String TABLE_NAME_REGISTER = "registration";
    public static  final String TABLE_NAME_ORDERS = "orders";
    public static  final String COL_1 = "ID";
    public static  final String COL_2 = "ProfilePicture";
    public static  final String COL_3 = "MemberNo";
    public static  final String COL_4 = "IdPassport";
    public static  final String COL_5 = "Surname";
    public static  final String COL_6 = "FirstName";
    public static  final String COL_7 = "MiddleName";
    public static  final String COL_8 = "PhoneNumber";
    public static  final String COL_9 = "DOB";
    public static  final String COL_10 = "Gender";
    public static  final String COL_11 = "County";
    public static  final String COL_12 = "Constituency";
    public static  final String COL_13 = "Ward";

    public static  final String COL_orderNumber = "OrderProduct";
    public static  final String COL_orderProduct = "OrderDate";
    public static  final String COL_orderDate = "OrderDate";
    public static  final String COL_quantity = "Quantity";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_REGISTER + "(ProfilePicture BLOB ,MemberNo TEXT UNIQUE NOT NULL, " +
                "IdPassport TEXT PRIMARY KEY NOT NULL,Surname TEXT NOT NULL,FirstName TEXT NOT NULL,MiddleName TEXT NOT NULL," +
                "PhoneNumber TEXT NOT NULL,DOB DATE NOT NULL,Gender TEXT NOT NULL," +
                "County TEXT NOT NULL,Constituency TEXT NOT NULL,Ward TEXT NOT NULL )");

      /*  db.execSQL("CREATE TABLE "+ TABLE_NAME_ORDERS + "(OrderNumber TEXT PRIMARY KEY , " +
                "OrderProduct TEXT  NOT NULL,OrderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,Quantity TEXT NOT NULL)");
*/
        final String SQL_CREATE_ORDERLIST_TABLE = "CREATE TABLE " +
                OrderContract.OrderEntry.TABLE_NAME + " (" +
                OrderContract.OrderEntry.COLUMN_ORDER_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                OrderContract.OrderEntry.COLUMN_ORDER_PRODUCT + " TEXT NOT NULL, " +
                OrderContract.OrderEntry.COLUMN_QUANTITY + " TEXT NOT NULL, " +
                OrderContract.OrderEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE_ORDERLIST_TABLE);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_REGISTER);
        db.execSQL("DROP TABLE IF EXISTS " + OrderContract.OrderEntry.TABLE_NAME);
        onCreate(db);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME_REGISTER + " where " + COL_3 +"=?" ,null);
        return cursor;
    }
}

