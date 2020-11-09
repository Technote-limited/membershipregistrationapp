package com.example.dhl.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhl.LoginResponse;
import com.example.dhl.R;
import com.example.dhl.SharedPrefManager;
import com.example.dhl.api.ApiClient;
import com.example.dhl.model.Agent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAgentProfileActivity extends AppCompatActivity {

    TextView idPassport, d_o_b,  tvgender, next_activity_tv;
    EditText agentName,phoneNumber,member_constituency,member_ward,membershipNo,member_county;
    Button updateDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_agent_profile);
        initializeViews();

        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);
        myToolBar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
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
        updateDetails = findViewById(R.id.save_profile_button);

        membershipNo.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getMember_number());
        idPassport.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getId_passport());
        agentName.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getAgentName());
        phoneNumber.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getPhone_number());
        d_o_b.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getDob());
        tvgender.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getGender());
        member_county.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getCounty());
        member_constituency.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getConstituency());
        member_ward.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getWard());

        updateDetails.setOnClickListener(v -> {
            updateAgent();
        });


    }

    private void updateAgent() {
        String member_number = membershipNo.getText().toString().trim();
        String agent_name = agentName.getText().toString().trim();
        String phone_number= phoneNumber.getText().toString().trim();
        String county = member_county.getText().toString().trim();
        String constituency = member_constituency.getText().toString().trim();
        String ward = member_ward.getText().toString().trim();


        Agent agent = SharedPrefManager.getInstance(getApplicationContext()).getAgent();

        Call<LoginResponse> call = ApiClient.getClient().updateAgent(agent.getId_passport(),
                member_number,agent_name,phone_number,county,constituency,ward);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                if (!response.body().isError()) {
                    SharedPrefManager.getInstance(getApplicationContext()).saveAgent(response.body().getAgent());
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }
}
