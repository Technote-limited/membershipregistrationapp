package com.example.dhl.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dhl.Activities.Display_Member_Activity;
import com.example.dhl.DatabaseHelper;
import com.example.dhl.MemberResponse;
import com.example.dhl.R;
import com.example.dhl.SharedPrefManager;
import com.example.dhl.api.ApiClient;
import com.example.dhl.model.Members;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchMembersActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    EditText membershipNo;
    String result="";
    FloatingActionButton searchMember;
    Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_members);

        openHelper = new DatabaseHelper(this);
        DatabaseHelper  databaseHelper = new DatabaseHelper(this);
        searchMember=findViewById(R.id.search_fab_button);
        membershipNo= findViewById(R.id.member_number);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);
        myToolBar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        CardView scanCardQR = findViewById(R.id.searchByQRCard);
        scanCardQR.setOnClickListener(v -> {
            IntentIntegrator intentIntegrator = new IntentIntegrator(this);
            intentIntegrator.initiateScan();

        });

        searchMember.setOnClickListener(v -> {
            //getMemberData();
            Intent intent = new Intent(this, Display_Member_Activity.class);
            startActivity(intent);

        //fetchFromMysql();
        //fetchFromSqlite();



        });

    }
   /* public void fetchFromMysql(){

        Call<List<Members>> call = ApiClient
                .getInstance()
                .getApi()
                .fetchByMemberNumber();

        call.enqueue(new Callback<List<Members>>() {
            @Override
            public void onResponse(Call<List<Members>> call, Response<List<Members>> response) {
                List<Members> membersList = response.body();

                String firstname = membersList.get(0).getFirst_name();
                String middlename = membersList.get(0).getMiddle_name();
                String surname = membersList.get(0).getSurname();
                String id_passport = membersList.get(0).getId_passport();
                String membernumber = membersList.get(0).getMember_number();
                String phonenumber = membersList.get(0).getPhone_number();
                String dob = membersList.get(0).getDob();
                String gender = membersList.get(0).getGender();
                String county = membersList.get(0).getCounty();
                String constituency = membersList.get(0).getConstituency();
                String ward = membersList.get(0).getWard();
                String photo = membersList.get(0).getMember_picture();


                Intent displayMember = new Intent(getApplicationContext(), Display_Member_Activity.class);
                displayMember.putExtra("fname",firstname);
                displayMember.putExtra("mname",middlename);
                displayMember.putExtra("surnam",surname);
                displayMember.putExtra("passport",id_passport);
                displayMember.putExtra("memberno",membernumber);
                displayMember.putExtra("pnumber",phonenumber);
                displayMember.putExtra("date",dob);
                displayMember.putExtra("female",gender);
                displayMember.putExtra("kiambu",county);
                displayMember.putExtra("kabete",constituency);
                displayMember.putExtra("wangige",ward);
                displayMember.putExtra("png",photo);
                startActivity(displayMember);



            }

            @Override
            public void onFailure(Call<List<Members>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), ""+t.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });


    }*/

    private void openDetailActivity(String[] data) {
        Intent intent = new Intent(SearchMembersActivity.this, Display_Member_Activity.class);
        intent.putExtra("NAME_KEY", data[0]);
        intent.putExtra("PROPELLANT_KEY", data[1]);
        intent.putExtra("DESTINATION_KEY", data[2]);
        intent.putExtra("TECHNOLOGY_EXISTS_KEY", data[3]);
        intent.putExtra("IMAGE_KEY", data[4]);
        startActivity(intent);
    }

   /* private void getMemberData() {
       *//* String first_name = firstName.getText().toString().trim();
        String middle_name = middleName.getText().toString().trim();
        String surname = member_surname.getText().toString().trim();
        String id_passport = idPassport.getText().toString().trim();
        String member_number = membershipNo.getText().toString().trim();
        String phone_number= phoneNumber.getText().toString().trim();
        String county = member_county.getText().toString().trim();
        String constituency = member_constituency.getText().toString().trim();
        String ward = member_ward.getText().toString().trim();*//*
       Members members = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        Call<MemberResponse> call = ApiClient.getInstance()
                .getApi().getMember(
                        members.getMember_number(),
                        members.getFirst_name(),members.getMiddle_name(),members.getSurname(),members.getId_passport(),
                        members.getPhone_number(),members.getCounty(),members.getConstituency(),members.getWard()

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



    public void fetchFromSqlite(){
        db=getApplicationContext().openOrCreateDatabase("member.db", Context.MODE_PRIVATE,null);
        Cursor cursor = db.rawQuery("SELECT * FROM registration where MemberNo=?", new String[]{result});

        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"No record Found",Toast.LENGTH_SHORT).show();
        }
        else{
            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()){
                buffer.append("Profile Picture : " + cursor.getBlob(0)+"\n");
                buffer.append("Member Number : " + cursor.getString(1)+"\n");
                buffer.append("ID/Passport : " + cursor.getString(2)+"\n");
                buffer.append("Surname : " + cursor.getString(3)+"\n");
                buffer.append("FirstName : " + cursor.getString(4)+"\n");
                buffer.append("Middle Name : " + cursor.getString(5)+"\n");
                buffer.append("Phone Number : " + cursor.getString(6)+"\n");
                buffer.append("DOB : " + cursor.getString(7)+"\n");
                buffer.append("Gender : " + cursor.getString(8)+"\n");
                buffer.append("County : " + cursor.getString(9)+"\n");
                buffer.append("Constituency : " + cursor.getString(10)+"\n");
                buffer.append("Ward : " + cursor.getString(11)+"\n");

                Toast.makeText(getApplicationContext(),buffer.toString(),Toast.LENGTH_LONG).show();

            }
        }}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult!= null){
            if(intentResult.getContents()== null){
                membershipNo.setText("cancelled");
            }
            else{
               membershipNo.setText(intentResult.getContents());


            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
