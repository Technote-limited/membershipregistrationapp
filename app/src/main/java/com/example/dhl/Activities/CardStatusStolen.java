package com.example.dhl.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dhl.R;

public class CardStatusStolen extends AppCompatActivity {
    TextView memberNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_status_stolen);
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
