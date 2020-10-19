package com.example.dhl.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dhl.api.ApiClient;
import com.example.dhl.model.Members;
import com.example.dhl.R;
import com.example.dhl.SharedPrefManager;
import com.example.dhl.Uploadresponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageCardsActivity extends AppCompatActivity {
    EditText memberNumber;
    Button lost,stolen,wornOut;
    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cards);

        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);
        myToolBar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        initialiseWidgets();
        onClickWidgets();

    }
    public void initialiseWidgets(){
        memberNumber = findViewById(R.id.editTextCard_member_number);
        lost = findViewById(R.id.button_lost_card);
        stolen = findViewById(R.id.button_stolen_card);
        wornOut = findViewById(R.id.button_worn_out_card);

    }
    public void onClickWidgets(){
        lost.setOnClickListener(v -> {

            if( TextUtils.isEmpty(memberNumber.getText())){
                memberNumber.setError( "member number is required!" );

            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                    reloadMembersFromDatabase();
                    Intent lostIntent = new Intent(ManageCardsActivity.this, CardStatusLost.class);
                    lostIntent.putExtra("EditTextMemberNumber",memberNumber.getText().toString());
                    startActivity(lostIntent);
                });
                builder.setNegativeButton("Cancel", (dialogInterface, i) -> {

                });
                AlertDialog dialog = builder.create();
                dialog.show();
               // deleteApi();
            }

        });
        stolen.setOnClickListener(v -> {
            if( TextUtils.isEmpty(memberNumber.getText())){
                memberNumber.setError( "member number is required!" );

            }else{

                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                    reloadMembersFromDatabase();
                    Intent stolenIntent = new Intent(ManageCardsActivity.this, CardStatusStolen.class);
                    stolenIntent.putExtra("EditTextMemberNumber",memberNumber.getText().toString());
                    startActivity(stolenIntent);
                });
                builder.setNegativeButton("Cancel", (dialogInterface, i) -> {

                });
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });
        wornOut.setOnClickListener(v -> {
            if( TextUtils.isEmpty(memberNumber.getText())){
                memberNumber.setError( "member number is required!" );

            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                    reloadMembersFromDatabase();
                    Intent wornOutIntent = new Intent(ManageCardsActivity.this,CardStatusStolen.class);
                    wornOutIntent  .putExtra("EditTextMemberNumber",memberNumber.getText().toString());
                    startActivity(wornOutIntent);
                });
                builder.setNegativeButton("Cancel", (dialogInterface, i) -> {

                });
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

    }
    private void reloadMembersFromDatabase() {
        mDatabase=getApplicationContext().openOrCreateDatabase("member.db", Context.MODE_PRIVATE,null);
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM registration", null);
        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"No record Found",Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            buffer.append("Image : " + cursor.getBlob(0)+"\n");
            buffer.append("Member Number : " + cursor.getString(1)+"\n");
            buffer.append("ID/Passport : " + cursor.getString(2)+"\n");
            buffer.append("Surname : " + cursor.getString(3)+"\n");
            buffer.append("FirstName : " + cursor.getString(4)+"\n");
            buffer.append("Middle Name : " + cursor.getString(5)+"\n");
            buffer.append("Phone Number : " + cursor.getString(6)+"\n");
            buffer.append("DOB : " + cursor.getString(7)+"\n");
            buffer.append("Gender : " + cursor.getString(8)+"\n");
            buffer.append("County : " + cursor.getString(9)+"\n");
            buffer.append("Constituency : " + cursor.getString(10)+"\n");
            buffer.append("Ward : " + cursor.getString(11)+"\n");

            Toast.makeText(getApplicationContext(),buffer.toString(),Toast.LENGTH_LONG).show();
            // AlertDialog.Builder buffer = new AlertDialog.Builder(this);
        }
    }
    public void deleteApi(){
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ManageCardsActivity.this);
            builder.setTitle("Are you sure?");
            builder.setMessage("This action will deactivate...");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Members members = SharedPrefManager.getInstance(ManageCardsActivity.this).getUser();
                    Call<Uploadresponse> call = ApiClient.getInstance().getApi().deleteUser(members.getId());

                    call.enqueue(new Callback<Uploadresponse>() {
                        @Override
                        public void onResponse(Call<Uploadresponse> call, Response<Uploadresponse> response) {

                            if (!response.body().isErr()) {
                                SharedPrefManager.getInstance(ManageCardsActivity.this);
                                SharedPrefManager.getInstance(ManageCardsActivity.this);
                                Intent intent = new Intent(ManageCardsActivity.this, CardStatusLost.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }

                            Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Uploadresponse> call, Throwable t) {

                        }
                    });

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            android.app.AlertDialog ad = builder.create();
            ad.show();
        }
}
