package com.example.dhl.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dhl.api.Api;
import com.example.dhl.api.ApiClient;
import com.example.dhl.LoginResponse;
import com.example.dhl.R;
import com.example.dhl.SharedPrefManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgentLoginActivity extends AppCompatActivity {
    FloatingActionButton logInFAB;
    TextInputEditText agentIdPassport, agentPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_login);
        initialiseViews();


        logInFAB.setOnClickListener(v -> {
            agentLogin();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void agentLogin() {
        String id_passport = agentIdPassport.getText().toString().trim();
        String password = agentPassword.getText().toString().trim();

        if (id_passport.isEmpty()) {
            agentIdPassport.setError("Id or Passport is required");
            agentIdPassport.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            agentPassword.setError("Password required");
            agentPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            agentPassword.setError("Password should be atleast 6 character long");
            agentPassword.requestFocus();
            return;
        }

        Call<LoginResponse> call = ApiClient.getClient().agentLogin(id_passport,password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if (!loginResponse.isError()) {

                    SharedPrefManager.getInstance(AgentLoginActivity.this)
                            .saveAgent(loginResponse.getAgent());

                    Intent intent = new Intent(AgentLoginActivity.this, ProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);


                }

                else {
                    Toast.makeText(AgentLoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Invalid Credentials....Please Try Again",Toast.LENGTH_LONG).show();
            }
        });
    }


    public void initialiseViews(){
        agentIdPassport= findViewById(R.id.editTextAgentIdPassport);
        agentPassword = findViewById(R.id.editTextAgentPassword);
        logInFAB =findViewById(R.id.log_in_fab);
        progressBar = new ProgressBar(this);
        progressBar.setMax(100);
    }


}
