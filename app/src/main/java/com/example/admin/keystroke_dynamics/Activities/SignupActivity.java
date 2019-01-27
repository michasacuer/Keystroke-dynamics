package com.example.admin.keystroke_dynamics.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.keystroke_dynamics.Signup.MailBody;
import com.example.admin.keystroke_dynamics.R;
import com.example.admin.keystroke_dynamics.Signup.Signup;
import com.example.admin.keystroke_dynamics.Signup.SignupListener;

public class SignupActivity extends AppCompatActivity implements SignupListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        signupButon = findViewById(R.id.btn_signup);
        nameText = findViewById(R.id.input_name);
        passwordText = findViewById(R.id.input_password);
        emailText = findViewById(R.id.input_email);

        signup = new Signup(SignupActivity.this, this);

        signupButon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(validate()) {
                    try {
                        Signup newSignup = new Signup(signup);
                        MailBody mailBody = new MailBody(getApplicationContext(), name, email, password);
                        newSignup.execute(name, email, password, mailBody.getBody(), getString(R.string.subject));
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), getString(R.string.signup_error), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public void onSignupPerformed(Boolean result){
        if(result) {
            Toast.makeText(getApplicationContext(), getString(R.string.signup_succed), Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.signup_error), Toast.LENGTH_LONG).show();
        }
    }

    private boolean validate() {

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
    private Signup signup;
    private EditText nameText;
    private EditText passwordText;
    private EditText emailText;

    private String name;
    private String password;
    private String email;
}
