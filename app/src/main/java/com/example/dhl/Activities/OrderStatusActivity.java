package com.example.dhl.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.dhl.R;

public class OrderStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);
        myToolBar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        Button viewOrderDetails = findViewById(R.id.buttonViewOrderDetails);
        viewOrderDetails.setOnClickListener(v -> {
            Intent viewDetails = new Intent(OrderStatusActivity.this, OrderTrackingActivity.class);
            startActivity(viewDetails);
        });
    }
}
