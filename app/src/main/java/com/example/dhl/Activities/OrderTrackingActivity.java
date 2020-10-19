package com.example.dhl.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.dhl.R;

public class OrderTrackingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tracking);
        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);
        myToolBar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }
}
