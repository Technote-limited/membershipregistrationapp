package com.example.dhl.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhl.DatabaseHelper;
import com.example.dhl.LoginResponse;
import com.example.dhl.SharedPrefManager;
import com.example.dhl.Uploadresponse;
import com.example.dhl.adapters.OrderAdapter;
import com.example.dhl.R;
import com.example.dhl.api.ApiClient;
import com.example.dhl.model.Agent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderPlacementActivity extends AppCompatActivity {
  Agent agent;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase mDatabase;
    Spinner card_spinner;
    Button makeOrder;
    EditText quantity,editTextCard;
    TextView orderDate, orderCreatedBy,order_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placement);

        openHelper = new DatabaseHelper(this);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);
        myToolBar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        initializeWidgets();
        setUpSpinner();



        makeOrder.setOnClickListener(v -> {

            if( TextUtils.isEmpty(quantity.getText())){
                quantity.setError( "quantity is required!" );

            }else{
                makeOrder();
                Intent i = new Intent(getApplicationContext(), OrderStatusActivity.class);
                startActivity(i);
            }

        });


    }
    public  void initializeWidgets(){
        card_spinner = findViewById(R.id.spinner_cards);
        makeOrder=findViewById(R.id.button_make_order);
        quantity = findViewById(R.id.EditTextQuantity);
        editTextCard =findViewById(R.id.eT_card_type);
        order_status = findViewById(R.id.TV_status);
        orderCreatedBy = findViewById(R.id.TV_createdBy);
        orderCreatedBy.setText(SharedPrefManager.getInstance(getApplicationContext()).getAgent().getAgentName());

        String date_now = new SimpleDateFormat("dd mm, yyyy", Locale.getDefault()).format(new Date());
        orderDate = findViewById(R.id.TV_order_date);
        orderDate.setText(date_now);


    }




    private void setUpSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.card_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        card_spinner.setAdapter(adapter);
        card_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();
                editTextCard.setText(item);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void makeOrder() {

        String cards = editTextCard.getText().toString();
        String order_quantity = quantity.getText().toString();
        String order_date= orderDate.getText().toString();
        String created_by = orderCreatedBy.getText().toString();

        int status = 1;
        order_status.setText(Integer.toString(status));





        Call<Uploadresponse> call = ApiClient.getClient()
                .createOrder(cards,order_quantity,order_date,created_by,status);


        call.enqueue(new Callback<Uploadresponse>() {
            @Override
            public void onResponse(Call<Uploadresponse> call, Response<Uploadresponse> response) {
                if (response.code() == 201) {

                    Uploadresponse dr = response.body();
                    Toast.makeText(OrderPlacementActivity.this, dr.getMsg(), Toast.LENGTH_LONG).show();

                } else if (response.code() == 422) {
                    Toast.makeText(OrderPlacementActivity.this, "An error Occurred...Try again", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Uploadresponse> call, Throwable t) {

                Toast.makeText(OrderPlacementActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });



    }
}
