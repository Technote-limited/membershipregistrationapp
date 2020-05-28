package com.example.dhl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dhl.barcode.BarCodeCaptureActivity;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import static com.example.dhl.MainActivity.BARCODE_READER_REQUEST_CODE;
import static com.github.mikephil.charting.charts.Chart.LOG_TAG;

public class SearchMembersActivity extends AppCompatActivity {
    EditText membershipNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_members);

        membershipNo= findViewById(R.id.member_number);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == BARCODE_READER_REQUEST_CODE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarCodeCaptureActivity.BarcodeObject);
                    Point[] cornerPoints = barcode.cornerPoints;
                    barcode.displayValue= membershipNo.toString();
                    membershipNo.setText(barcode.displayValue);
                    Toast.makeText(this, "We are scanning", Toast.LENGTH_SHORT).show();
                    Log.e("Activity Result", "Data captured");
                } else {
                    super.onActivityResult(requestCode, resultCode, data);
                    membershipNo.setText("Data is null");
                    Log.e("Activity Result", "Data is null");
                }
            } else {
                Log.e(LOG_TAG, String.format(getString(R.string.barcode_error_format),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
                Log.e("Activity Result", "Result code not success");
            }
        } else {
            Log.e("Activity Result", "Result code mismatch");
        }
    }

}
