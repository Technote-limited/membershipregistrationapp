package com.example.dhl.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.dhl.MemberResponse;
import com.example.dhl.R;
import com.example.dhl.SharedPrefManager;
import com.example.dhl.adapters.MembersAdapter;
import com.example.dhl.api.ApiClient;
import com.example.dhl.model.Members;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Member;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllMembersActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener {


    //RecyclerView recyclerView;

    MembersAdapter membersAdapter;
    private List<Members> membersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_members);
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

        navigationView.setOnNavigationItemSelectedListener(this);

        displayFragment(new MembersFragment());


    }

    private void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativeLayout, fragment)
                .commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch(item.getItemId()){
            case R.id.menu_home:
               // fragment = new HomeFragment();
                break;
            case R.id.menu_users:
                fragment = new MembersFragment();
                break;
            case R.id.menu_settings:
               // fragment = new SettingsFragment();
                break;
        }

        if(fragment != null){
            displayFragment(fragment);
        }
        return false;
    }
}
