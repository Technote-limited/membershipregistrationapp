package com.example.dhl.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhl.LoginResponse;
import com.example.dhl.MemberResponse;
import com.example.dhl.R;
import com.example.dhl.SharedPrefManager;
import com.example.dhl.Uploadresponse;
import com.example.dhl.api.ApiClient;
import com.example.dhl.model.Agent;
import com.example.dhl.model.Members;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Display_Member_Activity extends AppCompatActivity {
    TextView idPassport, member_surname, firstName, middleName, phoneNumber, d_o_b, member_constituency, member_ward,tvgender,
    membershipNo,member_county;
    ImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__member_);
        initializeViews();

        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);
        myToolBar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
       // getMemberData();
       // getValues();

    }

    @Override
    protected void onStart() {
        super.onStart();

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

      //  profile_image.setImageDrawable(SharedPrefManager.getInstance(getApplicationContext()).getAgent().get);
        firstName.setText(SharedPrefManager.getInstance(getApplicationContext()).getUser().getFirst_name());
        middleName.setText(SharedPrefManager.getInstance(getApplicationContext()).getUser().getMiddle_name());
        member_surname.setText(SharedPrefManager.getInstance(getApplicationContext()).getUser().getSurname());
        membershipNo.setText(SharedPrefManager.getInstance(getApplicationContext()).getUser().getMember_number());
        idPassport.setText(SharedPrefManager.getInstance(getApplicationContext()).getUser().getId_passport());
        phoneNumber.setText(SharedPrefManager.getInstance(getApplicationContext()).getUser().getPhone_number());
        d_o_b.setText(SharedPrefManager.getInstance(getApplicationContext()).getUser().getDob());
        tvgender.setText(SharedPrefManager.getInstance(getApplicationContext()).getUser().getGender());
        member_county.setText(SharedPrefManager.getInstance(getApplicationContext()).getUser().getCounty());
        member_constituency.setText(SharedPrefManager.getInstance(getApplicationContext()).getUser().getConstituency());
        member_ward.setText(SharedPrefManager.getInstance(getApplicationContext()).getUser().getWard());


    }

    /*private void getMemberData() {
        String first_name = firstName.getText().toString().trim();
        String middle_name = middleName.getText().toString().trim();
        String surname = member_surname.getText().toString().trim();
        String id_passport = idPassport.getText().toString().trim();
        String member_number = membershipNo.getText().toString().trim();
        String phone_number= phoneNumber.getText().toString().trim();
        String county = member_county.getText().toString().trim();
        String constituency = member_constituency.getText().toString().trim();
        String ward = member_ward.getText().toString().trim();


        Members members = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        Call<MemberResponse> call = ApiClient.getInstance()
                .getApi().getMember(
                        members.getMember_number(),
                        first_name,middle_name,surname,id_passport,phone_number,county,constituency,ward
                );

        call.enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {

                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                if (!response.body().isError()) {
                    SharedPrefManager.getInstance(getApplicationContext()).saveUser(response.body().getUser());

                }
            }

            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {

            }
        });
    }*/
    public void getValues(){
        Bundle b=getIntent().getExtras();
        if(b!=null)
        {
            String first = b.getString("fname");
            String middle = b.getString("mname");
            String surname  =b.getString("surnam");
            String idpass =b.getString("passport");
            String phone =b.getString("pnumber");
            String dob =b.getString("date");
            String gender =b.getString("female");
            String county =b.getString("kiambu");
            String constituency =b.getString("kabete");
            String ward=b.getString("wangige");
            String memberNo =b.getString("memberno");
            String pic=b.getString("png");

            firstName.setText(first);
            middleName.setText(middle);
            member_surname.setText(surname);
            idPassport.setText(idpass);
            phoneNumber.setText(phone);
            d_o_b.setText(dob);
            tvgender.setText(gender);
            member_county.setText(county);
            member_constituency.setText(constituency);
            member_ward.setText(ward);
            membershipNo.setText(memberNo);



        }

    }
}
