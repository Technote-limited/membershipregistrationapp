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
    //private RecyclerView mRecyclerView;
    //private ExampleAdapter mAdapter;
    //private RecyclerView.LayoutManager mLayoutManager;
    //final ArrayList<ExampleItem> exampleList = new ArrayList<>();
    CardView recruitMembers,searchMember,progress,manageCards,orderCards;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);
        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);
        /*exampleList.add(new ExampleItem(R.drawable.choose, "Recruit Members", ""));
        exampleList.add(new ExampleItem(R.drawable.search, "Search Members", ""));
        exampleList.add(new ExampleItem(R.drawable.progress, "My Progress", ""));
        exampleList.add(new ExampleItem(R.drawable.choose, "Manage Cards", ""));
        exampleList.add(new ExampleItem(R.drawable.search, "Order Cards", ""));
        exampleList.add(new ExampleItem(R.drawable.progress, "My Progress", ""));*/
        initialiseCardView();
        clickCards();


    }
    public  void initialiseCardView(){
        recruitMembers= findViewById(R.id.card_view_recruit);
        searchMember = findViewById(R.id.card_view_search);
        progress=findViewById(R.id.card_view_progress);
        manageCards=findViewById(R.id.card_view_manage_cards);
        orderCards = findViewById(R.id.card_view_order_cards);


    }
    public void clickCards(){
        recruitMembers.setOnClickListener(v -> {
            Intent intent = new Intent(AgentActivity.this,MainActivity.class);
            startActivity(intent);
        });


        searchMember.setOnClickListener(v -> {
            Intent searchIntent = new Intent(AgentActivity.this,SearchMembersActivity.class);
            startActivity(searchIntent);
        });
        progress.setOnClickListener(v -> {
            Intent intent = new Intent(AgentActivity.this,ProgressActivity.class);
            startActivity(intent);
        });

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
    /* public void callRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter= new ExampleAdapter(exampleList);
        mRecyclerView.setAdapter(mAdapter);
       *//* mAdapter.setOnItemClickListener(position -> {
            exampleList.get(position);
            Intent intent = new Intent(AgentActivity.this,MainActivity.class);
            startActivity(intent);
        });*//*
    }*/


}
