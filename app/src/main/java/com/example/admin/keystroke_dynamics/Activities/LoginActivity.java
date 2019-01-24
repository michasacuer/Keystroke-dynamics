package com.example.admin.keystroke_dynamics.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.keystroke_dynamics.DTO.User.User;
import com.example.admin.keystroke_dynamics.Login;
import com.example.admin.keystroke_dynamics.LoginListener;
import com.example.admin.keystroke_dynamics.R;

public class LoginActivity extends AppCompatActivity implements LoginListener {

    private Intent activityIntent;
    private Button loginButton;
    private EditText emailText;
    private EditText passwordText;
    private Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        loginButton = findViewById(R.id.btn_login);
        emailText = findViewById(R.id.input_email);
        passwordText = findViewById(R.id.input_password);
        login = new Login(getApplicationContext(), this);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                Login newLogin = new Login(login);
                try {
                    newLogin.execute(email, password).get();
                }
                catch(Exception e){ }
                if(loginValid) {
                    Toast.makeText(getApplicationContext(), "weszło", Toast.LENGTH_LONG).show();
                    loginValid = false;
                }
                else {
                    Toast.makeText(getApplicationContext(), "nieweszlo", Toast.LENGTH_LONG).show();
                    loginValid = false;
                }
            }
        });
    }

    public void goToSignupActivity(View view){
        activityIntent = new Intent(this, SignupActivity.class);
        startActivity(activityIntent);
    }

    @Override
    public void onLoginPerformed(Boolean result){
        this.loginValid = result;
    }

    private boolean loginValid;
    private String email;
    private String password;
}

