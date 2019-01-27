package com.example.admin.keystroke_dynamics.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.keystroke_dynamics.R;


public class AddMeasureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmeasure_activity);

        measureText = findViewById(R.id.input_measure);
        cancelButton = findViewById(R.id.btn_cancel);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private EditText measureText;
    private Button cancelButton;

}
