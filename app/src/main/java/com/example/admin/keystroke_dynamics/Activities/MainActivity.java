package com.example.admin.keystroke_dynamics.Activities;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.keystroke_dynamics.Adapter.ExpandableListAdapter;
import com.example.admin.keystroke_dynamics.Adapter.ExpandableListDataMeasures;
import com.example.admin.keystroke_dynamics.AddMeasure.ActualMeasure;
import com.example.admin.keystroke_dynamics.Classifier.kNN;
import com.example.admin.keystroke_dynamics.DTO.Measure.Measure;
import com.example.admin.keystroke_dynamics.DTO.Measure.MeasureViewModel;
import com.example.admin.keystroke_dynamics.DTO.User.User;
import com.example.admin.keystroke_dynamics.DTO.User.UserViewModel;
import com.example.admin.keystroke_dynamics.Login.LoggedUser;
import com.example.admin.keystroke_dynamics.R;
import com.example.admin.keystroke_dynamics.Utils.PreferenceEditor;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loggedUserSharedPreferenceEditor = new PreferenceEditor(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        header = navigationView.getHeaderView(0);
        emailText = header.findViewById(R.id.nav_text_email);
        usernameText = header.findViewById(R.id.nav_text_username);

        if(loggedUserSharedPreferenceEditor.isEmpty())
            startActivityForResult(new Intent(this, LoginActivity.class), REQUEST_CODE_LOGIN);

        else{
            loggedUser = loggedUser.getInstance();
            loggedUserSharedPreferenceEditor.load(loggedUser);
            emailText.setText(loggedUser.getEmail());
            usernameText.setText(loggedUser.getUsername());
        }

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                usersFromObserver = users;
            }
        });

        measureViewModel = ViewModelProviders.of(this).get(MeasureViewModel.class);
        measureViewModel.getAllMeasures().observe(this, new Observer<List<Measure>>() {
            @Override
            public void onChanged(@Nullable List<Measure> measures) {
                expandableListView = findViewById(R.id.expandable_listview);
                expandableListDataMeasures = new ExpandableListDataMeasures(getApplicationContext(), measures);
                expandableListDetail = expandableListDataMeasures.getData();
                expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                expandableListAdapter = new ExpandableListAdapter(getApplicationContext(), expandableListTitle, expandableListDetail, measures, usersFromObserver);
                expandableListView.setAdapter(expandableListAdapter);
                measuresFromObserver = measures;
            }
        });

        addMeasureButton = findViewById(R.id.floating_button_addMeasure);
        addMeasureButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivityForResult(new Intent(getApplicationContext(), AddMeasureActivity.class), REQUEST_CODE_ADD_MEASURE);
            }
        });

        classifyButton = findViewById(R.id.floating_button_classification);
        classifyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivityForResult(new Intent(getApplicationContext(), AddMeasureActivity.class), REQUEST_CODE_CLASSIFY);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_LOGIN:
                    loggedUser = loggedUser.getInstance();
                    loggedUserSharedPreferenceEditor.save(loggedUser);
                    emailText.setText(loggedUser.getEmail());
                    usernameText.setText(loggedUser.getUsername());
                    break;
                case REQUEST_CODE_ADD_MEASURE:
                    measureViewModel.insert(ActualMeasure.getInstance());
                    break;
                case REQUEST_CODE_CLASSIFY:
                    knn = new kNN();
                    int id = knn.execute(ActualMeasure.getInstance(), measuresFromObserver);
                    AlertDialog dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme_Dark_AlertDialog))
                            .setTitle(getString(R.string.classification_content) + " " + usersFromObserver.get(id-1).getUsername())
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                }
                            })
                            .setNegativeButton("Cancel", null).create();
                    dialog.show();
                    break;
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
            case R.id.nav_logout:
                loggedUser.resetInstance();
                startActivityForResult(new Intent(this, LoginActivity.class), REQUEST_CODE_LOGIN);
                loggedUserSharedPreferenceEditor.clear();
                Toast.makeText(getApplicationContext(), getString(R.string.logout), Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_info:
                InfoDialog infoDialog = new InfoDialog();
                infoDialog.show(getSupportFragmentManager(), "info dialog");
                break;
        }
        return true;
    }

    private static final int REQUEST_CODE_LOGIN = 0;
    private static final int REQUEST_CODE_ADD_MEASURE = 1;
    private static final int REQUEST_CODE_CLASSIFY = 2;
    private FloatingActionButton addMeasureButton;
    private FloatingActionButton classifyButton;
    private DrawerLayout drawerLayout;
    private LoggedUser loggedUser;
    private TextView emailText;
    private TextView usernameText;
    private PreferenceEditor loggedUserSharedPreferenceEditor;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListDataMeasures expandableListDataMeasures;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    private MeasureViewModel measureViewModel;
    private UserViewModel userViewModel;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private View header;
    private List<User> usersFromObserver;
    private List<Measure> measuresFromObserver;
    private kNN knn;
}
