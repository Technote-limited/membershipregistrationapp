package com.example.dhl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AgentLoginActivity extends AppCompatActivity {
    FloatingActionButton logInFAB;
    TextInputEditText agentEmail,agentPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_login);
        initialiseViews();

        mAuthListener = firebaseAuth -> {
            FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
            if(mFirebaseUser != null){
                Toast.makeText(getApplicationContext(),"logged in",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AgentLoginActivity.this,AgentActivity.class));
            }
            else{
                Toast.makeText(getApplicationContext(),"Please log in",Toast.LENGTH_SHORT).show();
            }
        };

        logInFAB.setOnClickListener(v -> {
            String email = agentEmail.getText().toString();
            String password = agentPassword.getText().toString();

            if(email.isEmpty()){
                agentEmail.setError("required");
                agentEmail.requestFocus();
            }
            else if(password.isEmpty()){
                agentPassword.setError("required");
                agentPassword.requestFocus();
            }
            else if( email.isEmpty() && password.isEmpty()){
                Toast.makeText(getApplicationContext(),"Fields are required",Toast.LENGTH_SHORT).show();
            }
            else if(!(email.isEmpty()  && password.isEmpty())){
               mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(AgentLoginActivity.this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                     if(!task.isSuccessful()) {
                         Toast.makeText(getApplicationContext(),"Login error please try again",Toast.LENGTH_SHORT).show();
                     }
                     else {
                         startActivity(new Intent(AgentLoginActivity.this,AgentActivity.class));
                     }
                   }
               });
            }
            else {
                Toast.makeText(getApplicationContext(),"An error has occurred",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void initialiseViews(){
        agentEmail= findViewById(R.id.editTextAgentEmail);
        agentPassword = findViewById(R.id.editTextAgentPassword);
        logInFAB =findViewById(R.id.log_in_fab);
    }
    public void OpenSignupPage(View view) {
        startActivity(new Intent(AgentLoginActivity.this,AgentRegister.class));
    }

}
