package com.example.dhl.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhl.MemberResponse;
import com.example.dhl.R;
import com.example.dhl.api.ApiClient;
import com.example.dhl.model.Members;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Display_Member_Activity extends AppCompatActivity {
    TextView idPassport, member_surname, firstName, middleName, phoneNumber, d_o_b, member_constituency, member_ward,tvgender,
    membershipNo,member_county;
    ImageView profile_image;
    Members memberResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__member);
        initializeViews();




    }



    public void initializeViews(){
        profile_image = findViewById(R.id.member_profile_image);
        membershipNo =findViewById(R.id.member_number);
        idPassport = findViewById(R.id.id_passport);
        member_surname = findViewById(R.id.surname);
        firstName = findViewById(R.id.first_name);
        middleName = findViewById(R.id.middle_name);
        phoneNumber = findViewById(R.id.phone_number);
        d_o_b = findViewById(R.id.dob);
        tvgender = findViewById(R.id.gender);
        member_county = findViewById(R.id.memberCounty);
        member_constituency = findViewById(R.id.constituency);
        member_ward = findViewById(R.id.ward);




            String first_name = memberResponse.getFirstName();
            String middle_name = memberResponse.getMiddleName();
            String surname= memberResponse.getSurname();
            String id_passport= memberResponse.getIdPassport();
            String member_number= memberResponse.getMemberNumber();
            String phone_number= memberResponse.getPhoneNumber();
            String dateofbirth = memberResponse.getDob();
            String gender = memberResponse.getGender();
            String county= memberResponse.getCounty();
            String constituency= memberResponse.getConstituency();
            String ward= memberResponse.getWard();



            firstName.setText(first_name);
            middleName.setText(middle_name);
            member_surname.setText(surname);
            idPassport.setText(id_passport);
            phoneNumber.setText(phone_number);
            d_o_b.setText(dateofbirth);
            tvgender.setText(gender);
            member_county.setText(county);
            member_constituency.setText(constituency);
            member_ward.setText(ward);
            membershipNo.setText(member_number);




        }


    }






