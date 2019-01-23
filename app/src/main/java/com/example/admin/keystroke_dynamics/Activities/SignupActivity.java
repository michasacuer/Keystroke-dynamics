package com.example.admin.keystroke_dynamics.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.keystroke_dynamics.MailBody;
import com.example.admin.keystroke_dynamics.R;
import com.example.admin.keystroke_dynamics.Signup;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        signupButon = findViewById(R.id.btn_signup);
        nameText = findViewById(R.id.input_name);
        passwordText = findViewById(R.id.input_password);
        emailText = findViewById(R.id.input_email);

        signupButon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(validate()) {
                    MailBody mailBody = new MailBody(getApplicationContext(), name, email, password);
                    signup.execute(name, email, password, mailBody.getBody(), getString(R.string.subject));
                    finish();
                }
            }
        });
    }

    public boolean validate() {
        boolean valid = true;

        name = nameText.getText().toString();
        email = emailText.getText().toString();
        password = passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameText.setError(getString(R.string.nameValidation));
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError(getString(R.string.emailValidation));
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            passwordText.setError(getString(R.string.passwordValidation));
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }

    private Button signupButon;
    private Signup signup = new Signup();
    private EditText nameText;
    private EditText passwordText;
    private EditText emailText;

    private String name;
    private String password;
    private String email;
}
