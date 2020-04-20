/*
package com.example.dhl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhl.camera.Camera;

public class LoginActivity extends AppCompatActivity {

    TextView Register;
    Button Login;
    EditText userName,userPassword;
    TextView Scanner;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();

        userName = findViewById(R.id.name);
        userPassword =  findViewById(R.id.password);

        Register = findViewById(R.id.TextViewRegister);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Login = findViewById(R.id.btnLogin);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = userName.getText().toString();
                String password = userPassword.getText().toString();
                cursor=db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_2 + "=? AND " + DatabaseHelper.COL_6 + "=?", new String[]{name,password});
                if(cursor!= null){
                  if(cursor.getCount()>0) {
                      cursor.moveToNext();
                      Toast.makeText(getApplicationContext(),"Welcome Aboard!",Toast.LENGTH_LONG).show();
                      Intent welcomeAboard = new Intent(LoginActivity.this,BoardingActivity.class);
                      startActivity(welcomeAboard);
                  }else{
                      Toast.makeText(getApplicationContext(),"Login Failure!",Toast.LENGTH_LONG).show();
                  }
                }

            }
        });

        Scanner=findViewById(R.id.simpleImageView);
        Scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCamera = new Intent(LoginActivity.this, Camera.class);
                startActivity(openCamera);
            }
        });


    }

}
*/
