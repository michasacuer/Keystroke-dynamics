package com.example.admin.keystroke_dynamics.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.admin.keystroke_dynamics.R;
import com.github.clans.fab.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        startActivityForResult(new Intent(this, LoginActivity.class), REQUEST_CODE);
        addMeasureActivity = new Intent(this, AddMeasureActivity.class);

        addMeasureButton = findViewById(R.id.floating_button_addMeasure);
        addMeasureButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(addMeasureActivity);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CODE){
            if(resultCode != Activity.RESULT_OK) {
                finish();
            }
        }
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer((GravityCompat.START));
        } else {
            super.onBackPressed();
        }
    }

    static final private int REQUEST_CODE = 0;
    private FloatingActionButton addMeasureButton;
    private Intent addMeasureActivity;
    private DrawerLayout drawerLayout;
}
