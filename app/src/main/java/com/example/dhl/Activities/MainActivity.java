package com.example.dhl.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhl.api.ApiClient;
import com.example.dhl.DatabaseHelper;
import com.example.dhl.R;
import com.example.dhl.Uploadresponse;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button register;
    ImageView profile_image , scannIcon;
    RadioGroup genderRadioGroup;
    RadioButton maleRadioButton, femaleRadioButton;
    private RadioButton radioSexButton;
    EditText idPassport, member_surname, firstName, middleName, phoneNumber, d_o_b, member_constituency, member_ward;
    TextView  membershipNo,member_county;
    DatePickerDialog datePickerDialog;
    Spinner countySpinner;
    private Bitmap bitmap;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int BARCODE_READER_REQUEST_CODE = 1;
    String path="";
    private static final int BUFFER_SIZE = 1024 * 2;
    private static final String IMAGE_DIRECTORY = "/broker_upload_gallery";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);
        myToolBar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
        initializeViews();
        displayDatePicker();
        setUpSpinner();


        register.setOnClickListener(v -> {
           ProgressDialog dialog = new ProgressDialog(getApplicationContext());
            dialog.setMessage("Saving...");
            dialog.show();


            saveInMysql();

        });

        profile_image.setOnClickListener(v -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }


        });
        scannIcon.setOnClickListener(v -> {
            IntentIntegrator intentIntegrator = new IntentIntegrator(this);
            intentIntegrator.initiateScan();


        });


    }

    public void saveInMysql(){
            byte[] profile_pic = imageViewtobyte(profile_image);

            String picUrl = new String(profile_pic);
            String idpassport = idPassport.getText().toString();
            String membernumber = membershipNo.getText().toString();
            String surname = member_surname.getText().toString();
            String firstname = firstName.getText().toString();
            String middlename = middleName.getText().toString();
            String phonenumber = phoneNumber.getText().toString();
            String gender = radioSexButton.getText().toString();
            String dob = d_o_b.getText().toString();
            String county = member_county.getText().toString();
            String constituency = member_constituency.getText().toString();
            String ward = member_ward.getText().toString();


            Call<Uploadresponse> call = ApiClient.getClient()
                    .createUser(idpassport,membernumber,surname,firstname,middlename,phonenumber,gender,dob,county,constituency,ward,picUrl);


            call.enqueue(new Callback<Uploadresponse>() {
                @Override
                public void onResponse(Call<Uploadresponse> call, Response<Uploadresponse> response) {
                    if (response.code() == 201) {

                        Uploadresponse dr = response.body();
                        Toast.makeText(MainActivity.this, dr.getMsg(), Toast.LENGTH_LONG).show();

                    } else if (response.code() == 422) {
                        Toast.makeText(MainActivity.this, "Member already exists", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Uploadresponse> call, Throwable t) {

                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });




    }
    public void saveInSqlite(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();
        db = openHelper.getWritableDatabase();

        byte[] profile_pic = imageViewtobyte(profile_image);
        String idpassport = idPassport.getText().toString();
        String membernumber = membershipNo.getText().toString();
        String surname = member_surname.getText().toString();
        String firstname = firstName.getText().toString();
        String middlename = middleName.getText().toString();
        String phonenumber = phoneNumber.getText().toString();
        String dob = d_o_b.getText().toString();
        String gender = Integer.toString(genderRadioGroup.getId());
        String county = member_county.getText().toString();
        String constituency = member_constituency.getText().toString();
        String ward = member_ward.getText().toString();

        insertData(profile_pic, idpassport,membernumber, surname, firstname, middlename, phonenumber, dob, gender, county, constituency, ward);
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Member Registered Successfully!", Toast.LENGTH_LONG).show();


    }
    public void insertData(byte[] profileImage, String idpassport,String memberNo, String surname, String firstname, String middlename, String phonenumber, String dob, String gender, String county, String constituency, String ward) {
        db = openHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, profileImage);
        contentValues.put(DatabaseHelper.COL_3,memberNo);
        contentValues.put(DatabaseHelper.COL_4, idpassport);
        contentValues.put(DatabaseHelper.COL_5, surname);
        contentValues.put(DatabaseHelper.COL_6, firstname);
        contentValues.put(DatabaseHelper.COL_7, middlename);
        contentValues.put(DatabaseHelper.COL_8, phonenumber);
        contentValues.put(DatabaseHelper.COL_9, dob);
        contentValues.put(DatabaseHelper.COL_10, gender);
        contentValues.put(DatabaseHelper.COL_11, county);
        contentValues.put(DatabaseHelper.COL_12, constituency);
        contentValues.put(DatabaseHelper.COL_13, ward);

        db.insert(DatabaseHelper.TABLE_NAME_REGISTER, null, contentValues);


    }
    private void setUpSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.county_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countySpinner.setAdapter(adapter);
        countySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();
                member_county.setText(item);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_member_details, menu);
        return true;
    }

       public void initializeViews() {
        openHelper = new DatabaseHelper(this);
        profile_image = findViewById(R.id.picture_icon);
        register = findViewById(R.id.btnRegister);
        membershipNo =findViewById(R.id.member_number);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
        idPassport = findViewById(R.id.id_passport);
        member_surname = findViewById(R.id.surname);
        firstName = findViewById(R.id.first_name);
        middleName = findViewById(R.id.middle_name);
        phoneNumber = findViewById(R.id.phone_number);
        d_o_b = findViewById(R.id.dob);
        genderRadioGroup = findViewById(R.id.radio_group);
        maleRadioButton = findViewById(R.id.radio_male);
        femaleRadioButton = findViewById(R.id.radio_female);
        countySpinner = findViewById(R.id.spinner);
        member_county = findViewById(R.id.memberCounty);
        member_constituency = findViewById(R.id.constituency);
        member_ward = findViewById(R.id.ward);
        scannIcon = findViewById(R.id.scan_icon);
           int selectedId = genderRadioGroup.getCheckedRadioButtonId();
           radioSexButton = (RadioButton) findViewById(selectedId);
           radioSexButton.getText();


       }

    public void displayDatePicker() {
        d_o_b.setOnClickListener(v -> {
            // calender class's instance and get current date , month and year from calender
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR); // current year
            int mMonth = c.get(Calendar.MONTH); // current month
            int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
            // date picker dialog
            datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        // set day of month , month and year value in the edit text
                        d_o_b.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);

                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        });
    }

    public static byte[] imageViewtobyte(ImageView image) {
        Bitmap bitmapp = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapp.compress(Bitmap.CompressFormat.PNG, 0, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            path = getFilePathFromURI(MainActivity.this, uri);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            profile_image.setImageBitmap(imageBitmap);

        }

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult!= null){
            if(intentResult.getContents()== null){
                membershipNo.setText("Data is null");
            }
            else{
                membershipNo.setText(intentResult.getContents());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
    public static String getFilePathFromURI(Context context, Uri contentUri) {
        //copy file and send new file path

        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        File copyFile = new File(wallpaperDirectory + File.separator + Calendar.getInstance()
                .getTimeInMillis()+".png");
        // create folder if not exists

        copy(context, contentUri, copyFile);
        Log.d("vPath--->",copyFile.getAbsolutePath());

        return copyFile.getAbsolutePath();

    }
    public static void copy(Context context, Uri srcUri, File dstFile) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(srcUri);
            if (inputStream == null) return;
            OutputStream outputStream = new FileOutputStream(dstFile);
            copystream(inputStream, outputStream);
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int copystream(InputStream input, OutputStream output) throws Exception, IOException {
        byte[] buffer = new byte[BUFFER_SIZE];

        BufferedInputStream in = new BufferedInputStream(input, BUFFER_SIZE);
        BufferedOutputStream out = new BufferedOutputStream(output, BUFFER_SIZE);
        int count = 0, n = 0;
        try {
            while ((n = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
                out.write(buffer, 0, n);
                count += n;
            }
            out.flush();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
            try {
                in.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
        }
        return count;
    }


}


