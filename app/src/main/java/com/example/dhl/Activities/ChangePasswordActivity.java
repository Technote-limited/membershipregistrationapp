package com.example.dhl.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dhl.R;
import com.example.dhl.SharedPrefManager;
import com.example.dhl.Uploadresponse;
import com.example.dhl.api.ApiClient;
import com.example.dhl.model.Agent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText editTextCurrentPassword, editTextNewPassword;
    Button changePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);
        myToolBar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        editTextCurrentPassword=findViewById(R.id.current_password);
        editTextNewPassword = findViewById(R.id.new_password);
        changePassword= findViewById(R.id.save_password_button);

        changePassword.setOnClickListener(v -> {
            updatePassword();
        });
    }
    private void updatePassword() {
        String currentpassword = editTextCurrentPassword.getText().toString().trim();
        String newpassword = editTextNewPassword.getText().toString().trim();

        if (currentpassword.isEmpty()) {
            editTextCurrentPassword.setError("Password required");
            editTextCurrentPassword.requestFocus();
            return;
        }

        if (newpassword.isEmpty()) {
            editTextNewPassword.setError("Enter new password");
            editTextNewPassword.requestFocus();
            return;
        }


        Agent agent = SharedPrefManager.getInstance(getApplicationContext()).getAgent();

        Call<Uploadresponse> call = ApiClient.getClient()
                .updatePassword(currentpassword, newpassword, agent.getId_passport());

        call.enqueue(new Callback<Uploadresponse>() {
            @Override
            public void onResponse(Call<Uploadresponse> call, Response<Uploadresponse> response) {

                Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Uploadresponse> call, Throwable t) {

            }
        });
    }
}
