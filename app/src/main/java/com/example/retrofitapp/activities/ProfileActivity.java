package com.example.retrofitapp.activities;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v4.app.Fragment;

import com.example.retrofitapp.Fragments.HomeFragment;
import com.example.retrofitapp.Fragments.SettingFragment;
import com.example.retrofitapp.Fragments.UsersFragment;
import com.example.retrofitapp.R;
import com.example.retrofitapp.Storage.SharedPrefManager;

public class ProfileActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        displayFragment(new HomeFragment());


    }

    private void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativeLayout, fragment)
                .commit();
    }



    @Override
    protected void onStart() {
        super.onStart();

        if(!SharedPrefManager.getInstance(this).isLogedIn()){
            Intent intent=new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment=null;
        switch (item.getItemId()){
            case R.id.menu_home:
                fragment= new HomeFragment();
                break;
            case R.id.menu_users:
                fragment=new UsersFragment();
                break;
            case R.id.menu_setting:
                fragment=new SettingFragment();
                break;
        }

        if (fragment!=null){

            displayFragment(fragment);
        }

        return false;
    }


}
