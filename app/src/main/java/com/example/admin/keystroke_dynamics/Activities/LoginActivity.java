package com.example.admin.keystroke_dynamics.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.admin.keystroke_dynamics.R;

public class LoginActivity extends AppCompatActivity {

    Intent activityIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }

    public void goToSignupActivity(View view){
        activityIntent = new Intent(this, SignupActivity.class);
        startActivity(activityIntent);
    }
}
