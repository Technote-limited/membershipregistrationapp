package com.example.dhl.Activities;



import android.os.Bundle;

import com.example.dhl.R;
import com.journeyapps.barcodescanner.CaptureActivity;

public class CapturePotraitActivity extends CaptureActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_potrait);
    }
}
