package com.example.admin.keystroke_dynamics.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.keystroke_dynamics.Login.Login;
import com.example.admin.keystroke_dynamics.Login.LoginListener;
import com.example.admin.keystroke_dynamics.R;

public class LoginActivity extends AppCompatActivity implements LoginListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        loginButton = findViewById(R.id.btn_login);
        emailText = findViewById(R.id.input_email);
        passwordText = findViewById(R.id.input_password);
        login = new Login(LoginActivity.this, this);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                Login newLogin = new Login(login);

                try {
                    newLogin.execute(email, password).get();
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), getString(R.string.login_error), Toast.LENGTH_LONG).show();
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
        if(result) {
            Toast.makeText(getApplicationContext(), getString(R.string.login_succed), Toast.LENGTH_LONG).show();
            setResult(Activity.RESULT_OK);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), getString(R.string.login_error), Toast.LENGTH_LONG).show();
        }
    }

    private String email;
    private String password;
    private Intent activityIntent;
    private Button loginButton;
    private EditText emailText;
    private EditText passwordText;
    private Login login;
}

