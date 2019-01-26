package com.example.admin.keystroke_dynamics.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.example.admin.keystroke_dynamics.R;


public class AddMeasureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmeasure_activity);
        measureText = findViewById(R.id.input_measure);
    }

    private EditText measureText;

}
