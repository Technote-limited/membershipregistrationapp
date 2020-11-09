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
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
    FloatingActionButton searchMember;
    TextView viewAllMembers;
    Members member;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_members);


        searchMember=findViewById(R.id.search_fab_button);
        membershipNo= findViewById(R.id.member_number);
        viewAllMembers=findViewById(R.id.TextViewAllMembers);


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

            getMemberData();

            Intent intent = new Intent(this, Display_Member_Activity.class);

            startActivity(intent);


        });

        viewAllMembers.setOnClickListener(v -> {
            Intent viewAllIntent = new Intent(this, ViewAllMembersActivity.class);
            startActivity(viewAllIntent);
        });

    }

    private void getMemberData() {
        String member_number = membershipNo.getText().toString().trim();

        Call<MemberResponse> call = ApiClient.getClient().getMember(member_number);

        call.enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {

                member =   response.body().getMember();
                Log.d("tag", "onResponse: " + member);


            }

            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Member Number Doesn't Exist",Toast.LENGTH_LONG).show();
            }
        });
    }








   /* public void fetchFromSqlite(){
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
        }}*/


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
