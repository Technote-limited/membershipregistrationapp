package com.example.dhl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dhl.Activities.AgentActivity;
import com.example.dhl.Activities.AgentLoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class AgentRegister extends AppCompatActivity {
    TextView tvAgentLogin;
    FloatingActionButton signUpFAB;
    TextInputEditText agentName,agentEmail,agentPhoneNumber,agentPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_register);
        initialiseViews();
        mAuth = FirebaseAuth.getInstance();
    }
    public  void initialiseViews(){
        tvAgentLogin=findViewById(R.id.textViewAgentLogin);
        signUpFAB = findViewById(R.id.fabSignUp);
        agentName = findViewById(R.id.editTextAgentName);
        agentEmail = findViewById(R.id.editTextAgentEmail);
        agentPhoneNumber = findViewById(R.id.editTextAgentPhoneNumber);
        agentPassword = findViewById(R.id.editTextAgentPassword);
        tvAgentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AgentRegister.this, AgentLoginActivity.class);
                startActivity(intent);
            }
        });



//        signUpFAB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent=new Intent(AgentRegister.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });

        signUpFAB.setOnClickListener(v -> {
            String name = agentName.getText().toString();
            String email = agentEmail.getText().toString();
            String phoneNumber = agentPhoneNumber.getText().toString();
            String password = agentPassword.getText().toString();

            if(name.isEmpty()){
                agentName.setError("required");
                agentName.requestFocus();
            }
            else if(email.isEmpty()){
                agentEmail.setError("required");
                agentEmail.requestFocus();
            }
            else if(phoneNumber.isEmpty()){
                agentPhoneNumber.setError("required");
                agentPhoneNumber.requestFocus();
            }
            else if(password.isEmpty()){
                agentPassword.setError("required");
                agentPassword.requestFocus();
            }
            else if(name.isEmpty() && email.isEmpty() && phoneNumber.isEmpty() && password.isEmpty()){
                Toast.makeText(getApplicationContext(),"Fields are required",Toast.LENGTH_SHORT).show();
            }
            else if(!(name.isEmpty() && email.isEmpty() && phoneNumber.isEmpty() && password.isEmpty())){
            mAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(AgentRegister.this, task -> {
                if(!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Registration Unsuccessful, Try again",Toast.LENGTH_SHORT).show();
                }
                else{
                    startActivity(new Intent(AgentRegister.this, AgentActivity.class));
                }
            });
            }
            else {
                Toast.makeText(getApplicationContext(),"An error has occurred",Toast.LENGTH_SHORT).show();
            }
        });

    }



}
