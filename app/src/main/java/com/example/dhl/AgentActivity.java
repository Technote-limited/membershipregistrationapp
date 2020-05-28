package com.example.dhl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AgentActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    final ArrayList<ExampleItem> exampleList = new ArrayList<>();
    CardView recruitMembers,searchMember,progress,manageCards,orderCards,myInventory;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);
        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);
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
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(AgentActivity.this,AgentLoginActivity.class);
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
        mAdapter.setOnItemClickListener(position -> {
            exampleList.get(position);

        });
    }


}
