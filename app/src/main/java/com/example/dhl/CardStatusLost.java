package com.example.dhl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CardStatusLost extends AppCompatActivity {
    TextView memberNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_status_lost);
        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);
        myToolBar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        memberNumber = findViewById(R.id.tv_member_number);
        Intent intent = getIntent();
        String memberNo = intent.getStringExtra("EditTextMemberNumber");

        memberNumber.setText(memberNo);

    }
}
