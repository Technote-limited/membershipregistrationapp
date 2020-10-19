package com.example.dhl.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.dhl.ExampleAdapter;
import com.example.dhl.ExampleItem;
import com.example.dhl.R;
import com.example.dhl.SharedPrefManager;

import java.util.ArrayList;

public class AgentActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    final ArrayList<ExampleItem> exampleList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);
        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);


/*
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }*/


        exampleList.add(new ExampleItem(R.drawable.candidates, "Recruit Members", ""));
        exampleList.add(new ExampleItem(R.drawable.searchh, "Search Members", ""));
        exampleList.add(new ExampleItem(R.drawable.myprogress, "My Progress", ""));
        exampleList.add(new ExampleItem(R.drawable.membership, "Manage Cards", ""));
        exampleList.add(new ExampleItem(R.drawable.order, "Order Cards", ""));
        exampleList.add(new ExampleItem(R.drawable.inventory, "Inventory", ""));
        callRecyclerView();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.log_out){
            finish();
            SharedPrefManager.getInstance(getApplicationContext()).logout();
            Intent intent = new Intent(AgentActivity.this, AgentLoginActivity.class);
            startActivity(intent);
        }

        if(item.getItemId()== R.id.profile){
            Intent intent = new Intent(AgentActivity.this, ProfileActivity.class);
            startActivity(intent);
        }

        if(item.getItemId()== R.id.change_password){
            Intent intent = new Intent(AgentActivity.this, ChangePasswordActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);


    }
     public void callRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter= new ExampleAdapter(exampleList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(exampleList::get);
    }


}
