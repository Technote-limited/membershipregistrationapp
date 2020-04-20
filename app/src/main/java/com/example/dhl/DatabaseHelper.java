package com.example.dhl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static  final String DATABASE_NAME = "member.db";
    public static  final String TABLE_NAME = "registration";
    public static  final String COL_1 = "ID";
    public static  final String COL_2 = "ProfilePicture";
    public static  final String COL_3 = "IdPassport";
    public static  final String COL_4 = "Surname";
    public static  final String COL_5 = "FirstName";
    public static  final String COL_6 = "MiddleName";
    public static  final String COL_7 = "PhoneNumber";
    public static  final String COL_8 = "DOB";
    public static  final String COL_9 = "Gender";
    public static  final String COL_10 = "County";
    public static  final String COL_11 = "Constituency";
    public static  final String COL_12 = "Ward";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ProfilePicture BLOB ,MemberNo TEXT UNIQUE NOT NULL, IdPassport TEXT PRIMARY KEY NOT NULL,Surname TEXT NOT NULL,FirstName TEXT NOT NULL,MiddleName TEXT NOT NULL,PhoneNumber TEXT NOT NULL,DOB DATE NOT NULL,Gender TEXT NOT NULL,County TEXT NOT NULL,Constituency TEXT NOT NULL,Ward TEXT NOT NULL )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

