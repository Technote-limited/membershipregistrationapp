package com.example.dhl.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dhl.Activities.AgentActivity;
import com.example.dhl.R;
import com.example.dhl.SharedPrefManager;
import com.google.android.material.button.MaterialButton;

public class ProfileActivity extends AppCompatActivity {
    TextView idPassport, agentName, phoneNumber, d_o_b, member_constituency, member_ward,tvgender,
            membershipNo,member_county,next_activity_tv;
    Button editDetailsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initializeViews();

        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);
        myToolBar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

    }



    @Override
    protected void onStart() {
        super.onStart();

       /* if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }*/
    }
    public void initializeViews(){
        membershipNo =findViewById(R.id.member_number);
        idPassport = findViewById(R.id.id_passport);
        agentName = findViewById(R.id.agent_name);
        phoneNumber = findViewById(R.id.phone_number);
        d_o_b = findViewById(R.id.dob);
        tvgender = findViewById(R.id.gender);
        member_county = findViewById(R.id.memberCounty);
        member_constituency = findViewById(R.id.constituency);
        member_ward = findViewById(R.id.ward);
        next_activity_tv = findViewById(R.id.move_next);
        editDetailsButton = findViewById(R.id.edit_profile_button);

        next_activity_tv.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),AgentActivity.class);
            startActivity(intent);
        });

        editDetailsButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),EditAgentProfileActivity.class);
            startActivity(intent);
        });

        membershipNo.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getMember_number());
        idPassport.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getId_passport());
        agentName.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getAgentName());
        phoneNumber.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getPhone_number());
        d_o_b.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getDob());
        tvgender.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getGender());
        member_county.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getCounty());
        member_constituency.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getConstituency());
        member_ward.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getWard());

    }

}
