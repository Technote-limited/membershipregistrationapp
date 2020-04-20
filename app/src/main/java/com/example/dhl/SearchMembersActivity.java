package com.example.dhl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.example.dhl.barcode.BarCodeCaptureActivity;

import static com.example.dhl.MainActivity.BARCODE_READER_REQUEST_CODE;

public class SearchMembersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_members);
        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);
        myToolBar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        CardView scanCardQR = findViewById(R.id.searchByQRCard);
        scanCardQR.setOnClickListener(v -> {
            Intent openCamera = new Intent(SearchMembersActivity.this, BarCodeCaptureActivity.class);
            startActivityForResult(openCamera,BARCODE_READER_REQUEST_CODE);
        });
    }

}
