package com.example.dhl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ManageCardsActivity extends AppCompatActivity {
    EditText memberNumber;
    Button lost,stolen,wornOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cards);

        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);
        myToolBar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        initialiseWidgets();
        onClickWidgets();

    }
    public void initialiseWidgets(){
        memberNumber = findViewById(R.id.editTextCard_member_number);
        lost = findViewById(R.id.button_lost_card);
        stolen = findViewById(R.id.button_stolen_card);
        wornOut = findViewById(R.id.button_worn_out_card);
    }
    public void onClickWidgets(){
        lost.setOnClickListener(v -> {

            if( TextUtils.isEmpty(memberNumber.getText())){
                memberNumber.setError( "member number is required!" );

            }else{
                Intent lostIntent = new Intent(ManageCardsActivity.this,CardStatusLost.class);
                lostIntent.putExtra("EditTextMemberNumber",memberNumber.getText().toString());
                startActivity(lostIntent);
            }

        });
        stolen.setOnClickListener(v -> {
            if( TextUtils.isEmpty(memberNumber.getText())){
                memberNumber.setError( "member number is required!" );

            }else{
                Intent stolenIntent = new Intent(ManageCardsActivity.this,CardStatusStolen.class);
                stolenIntent.putExtra("EditTextMemberNumber",memberNumber.getText().toString());
                startActivity(stolenIntent);

            }
        });
        wornOut.setOnClickListener(v -> {
            if( TextUtils.isEmpty(memberNumber.getText())){
                memberNumber.setError( "member number is required!" );

            }else{
                Intent wornOutIntent = new Intent(ManageCardsActivity.this,CardStatusStolen.class);
                wornOutIntent  .putExtra("EditTextMemberNumber",memberNumber.getText().toString());
                startActivity(wornOutIntent);
            }
        });
    }
}
