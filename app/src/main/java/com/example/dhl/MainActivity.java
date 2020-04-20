package com.example.dhl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhl.barcode.BarCodeCaptureActivity;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    String gender = "";
    EditText idPassport, member_surname, firstName, middleName, phoneNumber, d_o_b, member_constituency, member_ward, member_county;
    TextView Login, QrCodeScanner, membershipNo;
    DatePickerDialog datePickerDialog;
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

        profile_image.setOnClickListener(v -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }


        });
        scannIcon.setOnClickListener(v -> {
            Intent openCamera = new Intent(MainActivity.this, BarCodeCaptureActivity.class);
            startActivityForResult(openCamera, BARCODE_READER_REQUEST_CODE);
        });


//        register.setOnClickListener(v -> {
//            db = openHelper.getWritableDatabase();
//
//            byte[] profile_pic = imageViewtobyte(profile_image);
              String idpassport = idPassport.getText().toString();
//            String surname = member_surname.getText().toString();
//            String firstname = firstName.getText().toString();
//            String middlename = middleName.getText().toString();
//            String phonenumber = phoneNumber.getText().toString();
//            String dob = d_o_b.getText().toString();
//            String gender = Integer.toString(genderRadioGroup.getId());
//            String county = member_county.getText().toString();
//            String constituency = member_constituency.getText().toString();
//            String ward = member_ward.getText().toString();
//            if (TextUtils.isEmpty(member_surname.getText())) {
//                member_surname.setError(" required!");
//              /* firstName.setError( "required!" );
//               middleName.setError( "required!" );
//               phoneNumber.setError( "required!" );
//               d_o_b.setError( " required!" );
//               member_county.setError( "required!" );
//               member_constituency.setError( "required!" );
//               member_ward.setError( "required!" );*/
//
//            } else {
//                Intent welcome = new Intent(MainActivity.this, BoardingActivity.class);
//                startActivity(welcome);
//            }
//
//
//            insertData(profile_pic, idpassport, surname, firstname, middlename, phonenumber, dob, gender, county, constituency, ward);
//            Toast.makeText(getApplicationContext(), "User Registered Successfully!", Toast.LENGTH_LONG).show();
//            Intent welcome = new Intent(MainActivity.this, BoardingActivity.class);
//            welcome.putExtra("EditTextID", idPassport.getText().toString());
//            welcome.putExtra("EditTextSurname", member_surname.getText().toString());
//            welcome.putExtra("EditTextFirstName", firstName.getText().toString());
//            welcome.putExtra("EditTextMiddleName", middleName.getText().toString());
//            welcome.putExtra("EditTextPhoneNumber", phoneNumber.getText().toString());
//            welcome.putExtra("EditTextDOB", d_o_b.getText().toString());
//            welcome.putExtra("EditTextCounty", member_county.getText().toString());
//            welcome.putExtra("EditTextConstituency", member_constituency.getText().toString());
//            welcome.putExtra("EditTextWard", member_ward.getText().toString());
//
//            startActivity(welcome);
//
//
//        });

        register.setOnClickListener(v -> {

          final   ProgressDialog pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("loading");
            pd.show();
            String tag="insert";
            String idPassport1=idPassport.getText().toString();
            String member_surname1=member_surname.getText().toString();
            String firstName1=firstName.getText().toString();
            String middleName1=middleName.getText().toString();
            String phoneNumber1=phoneNumber.getText().toString();
            String d_o_b1=d_o_b.getText().toString();
            String maleRadioButton1=maleRadioButton.getText().toString();
            String femaleRadioButton1=femaleRadioButton.getText().toString();
            String member_county1=member_county.getText().toString();
            String member_constituency1=member_constituency.getText().toString();
            String member_ward1=member_ward.getText().toString();
            String membershipNo1=membershipNo.getText().toString();
            String gender = Integer.toString(genderRadioGroup.getId());
            genderRadioGroup.setOnCheckedChangeListener((rg, checkedId) -> {
                for(int i=0; i<rg.getChildCount(); i++) {
                    RadioButton btn = (RadioButton) rg.getChildAt(i);
                    if(btn.getId() == checkedId) {
                        String text = (String) btn.getText();
                        return ;
                    }
                }
            });


            RequestBody tag1 = RequestBody.create(MediaType.parse("text/plain"), tag);
            RequestBody idPassport11 = RequestBody.create(MediaType.parse("text/plain"), idPassport1);
            RequestBody surname1 = RequestBody.create(MediaType.parse("text/plain"), member_surname1);
            RequestBody firstName11 = RequestBody.create(MediaType.parse("text/plain"), firstName1);
            RequestBody middleName11 = RequestBody.create(MediaType.parse("text/plain"), middleName1);
            RequestBody phoneNumber11 = RequestBody.create(MediaType.parse("text/plain"), phoneNumber1);
            RequestBody d_o_b11 = RequestBody.create(MediaType.parse("text/plain"), d_o_b1);
            RequestBody maleRadioButton11 = RequestBody.create(MediaType.parse("text/plain"), "male");
            // RequestBody femaleRadioButton11 = RequestBody.create(MediaType.parse("text/plain"), femaleRadioButton1);
            RequestBody member_county11 = RequestBody.create(MediaType.parse("text/plain"), member_county1);
            RequestBody member_constituency11 = RequestBody.create(MediaType.parse("text/plain"), member_constituency1);
            RequestBody member_ward11 = RequestBody.create(MediaType.parse("text/plain"), member_ward1);
            RequestBody membershipNo11 = RequestBody.create(MediaType.parse("text/plain"), membershipNo1);
            String pdfname = String.valueOf(Calendar.getInstance().getTimeInMillis());
            // Parsing any Media type file
            File file = new File(path);
            final RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("picture", file.getName(), requestBody);
            RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), path);

            if (path.isEmpty())
            {
                fileToUpload=null;
                filename=null;
            }

            Register_data_retrofit.getInstance()
                    .geteditprofile()
                    .uploadData(tag1,idPassport11,surname1,firstName11,middleName11,phoneNumber11,d_o_b11,maleRadioButton11,member_county11,member_constituency11,member_ward11,membershipNo11,filename)
                    .enqueue(new Callback<Uploadresponse>() { //,fileToUpload
                        @Override
                        public void onResponse(Call<Uploadresponse> call, Response<Uploadresponse> response) {
                            Uploadresponse Uploadresponse = response.body();
                            pd.dismiss();
                            Toast.makeText(MainActivity.this, "Data Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("gttt", call.toString());
                            pd.dismiss();
                            Toast.makeText(MainActivity.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });

        });

      /*  ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,counties);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countySpinner.setAdapter(dataAdapter);
        member_county.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    countySpinner.setVisibility(View.INVISIBLE);
                }
            }
        });
       countySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               member_county.setText(countySpinner.getSelectedItem().toString());
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
*/
        idPassport.addTextChangedListener(registerTextWatcher);
        member_surname.addTextChangedListener(registerTextWatcher);
        firstName.addTextChangedListener(registerTextWatcher);
        middleName.addTextChangedListener(registerTextWatcher);
        phoneNumber.addTextChangedListener(registerTextWatcher);
        d_o_b.addTextChangedListener(registerTextWatcher);
        member_county.addTextChangedListener(registerTextWatcher);
        member_constituency.addTextChangedListener(registerTextWatcher);
        member_ward.addTextChangedListener(registerTextWatcher);

    }

    private TextWatcher registerTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String idPassportInput = idPassport.getText().toString().trim();
            String surnameInput = member_surname.getText().toString().trim();
            String firstNameInput = firstName.getText().toString().trim();
            String middleNameInput = middleName.getText().toString().trim();
            String phoneNumberInput = phoneNumber.getText().toString().trim();
            String dobInput = d_o_b.getText().toString().trim();
            String countyInput = member_county.getText().toString().trim();
            String constituencyInput = member_constituency.getText().toString().trim();
            String wardInput = member_ward.getText().toString().trim();
            if (idPassportInput.isEmpty() || surnameInput.isEmpty() || firstNameInput.isEmpty()
                    || middleNameInput.isEmpty() || phoneNumberInput.isEmpty() || dobInput.isEmpty() || countyInput.isEmpty()
                    || constituencyInput.isEmpty() || wardInput.isEmpty()) {
                register.setEnabled(true);

            } else {
                //what to perform if is enabled
                //Toast.makeText(MainActivity.this, "Register is enabled", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

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
        membershipNo = findViewById(R.id.member_number);
        idPassport = findViewById(R.id.id_passport);
        member_surname = findViewById(R.id.surname);
        firstName = findViewById(R.id.first_name);
        middleName = findViewById(R.id.middle_name);
        phoneNumber = findViewById(R.id.phone_number);
        d_o_b = findViewById(R.id.dob);
        genderRadioGroup = findViewById(R.id.radio_group);
        maleRadioButton = findViewById(R.id.radio_male);
        femaleRadioButton = findViewById(R.id.radio_female);
        member_county = findViewById(R.id.county);
        member_constituency = findViewById(R.id.constituency);
        member_ward = findViewById(R.id.ward);
        scannIcon = findViewById(R.id.scan_icon);


        genderRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == R.id.radio_female) {
                gender = "Female";
            } else if (i == R.id.radio_male) {
                gender = "Male";
            }
        });

        //register.setEnabled(false);

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

    public void insertData(byte[] profileImage, String idpassport, String surname, String firstname, String middlename, String phonenumber, String dob, String gender, String county, String constituency, String ward) {
        db = openHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, profileImage);
        contentValues.put(DatabaseHelper.COL_3, idpassport);
        contentValues.put(DatabaseHelper.COL_4, surname);
        contentValues.put(DatabaseHelper.COL_5, firstname);
        contentValues.put(DatabaseHelper.COL_6, middlename);
        contentValues.put(DatabaseHelper.COL_7, phonenumber);
        contentValues.put(DatabaseHelper.COL_8, dob);
        contentValues.put(DatabaseHelper.COL_9, gender);
        contentValues.put(DatabaseHelper.COL_10, county);
        contentValues.put(DatabaseHelper.COL_11, constituency);
        contentValues.put(DatabaseHelper.COL_12, ward);

        db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);


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
                    membershipNo.setText("Data is null");//apa
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


