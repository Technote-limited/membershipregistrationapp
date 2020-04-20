/*
package com.example.dhl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BoardingActivity extends AppCompatActivity {
    TextView idPassport,member_surname,firstName,middleName,phoneNumber,d_o_b,member_county,member_constituency,member_ward;
    ImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarding);

        profilePicture = findViewById(R.id.picture_icon);
        idPassport = findViewById(R.id.id_passport);
        member_surname = findViewById(R.id.surname);
        firstName = findViewById(R.id.first_name);
        middleName =findViewById(R.id.middle_name);
        phoneNumber= findViewById(R.id.phone_number);
        d_o_b = findViewById(R.id.dob);
        member_county= findViewById(R.id.county);
        member_constituency = findViewById(R.id.constituency);
        member_ward = findViewById(R.id.ward);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bitmap image = (Bitmap) extras.get("image");
            if (image != null) {
                profilePicture.setImageBitmap(image);
            }
        }

        Intent intent = getIntent();
        String idpass = intent.getStringExtra("EditTextID");
        String surname = intent.getStringExtra("EditTextSurname");
        String firstname = intent.getStringExtra("EditTextFirstName");
        String middlename = intent.getStringExtra("EditTextMiddleName");
        String phonenumber = intent.getStringExtra("EditTextPhoneNumber");
        String dob = intent.getStringExtra("EditTextDOB");
        String county = intent.getStringExtra("EditTextCounty");
        String constituency = intent.getStringExtra("EditTextConstituency");
        String ward = intent.getStringExtra("EditTextWard");


        idPassport.setText(idpass);
        member_surname.setText(surname);
        firstName.setText(firstname);
        middleName.setText(middlename);
        phoneNumber.setText(phonenumber);
        d_o_b.setText(dob);
        member_county.setText(county);
        member_constituency.setText(constituency);
        member_ward.setText(ward);
    }

}
*/
