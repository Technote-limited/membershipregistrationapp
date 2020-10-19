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
import android.widget.Toast;

import com.example.dhl.DatabaseHelper;
import com.example.dhl.OrderAdapter;
import com.example.dhl.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderPlacementActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    SQLiteOpenHelper openHelper;
    SQLiteDatabase mDatabase;
    private OrderAdapter mAdapter;
    Spinner spinner;
    Button makeOrder;
    EditText quantity;
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
         spinner = findViewById(R.id.spinner);
         makeOrder=findViewById(R.id.button_make_order);
         quantity = findViewById(R.id.EditTextQuantity);

        spinner.setOnItemSelectedListener(this);
        List<String> cardType = new ArrayList<String>();
        cardType.add("Youth League");
        cardType.add("Women League");
        cardType.add("Disability League");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cardType);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);




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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void makeOrder() {
        String product = spinner.getSelectedItem().toString();
        String valueQuantity =quantity.getText().toString();

        //getting the current time for order date
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String orderDate = sdf.format(cal.getTime());

          /*  String insertSQL = "INSERT INTO orders \n" +
                    "( OrderProduct, OrderDate, Quantity)\n" +
                    "VALUES \n" +
                    "(?, ?, ?);";

            mDatabase.execSQL(insertSQL, new String[]{ product,valueQuantity, orderDate});
*/
            Toast.makeText(this, " Ordered Successfully", Toast.LENGTH_SHORT).show();


    }
}
