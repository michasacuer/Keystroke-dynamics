package com.example.admin.keystroke_dynamics.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.keystroke_dynamics.AddMeasure.ActualMeasure;
import com.example.admin.keystroke_dynamics.DTO.Measure.Measure;
import com.example.admin.keystroke_dynamics.R;
import com.example.admin.keystroke_dynamics.Utils.PreferenceEditor;

import static java.lang.Math.toIntExact;

public class AddMeasureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmeasure_activity);
        measure = ActualMeasure.getInstance();

        preferenceEditor = new PreferenceEditor(getApplicationContext());
        measureText = findViewById(R.id.input_measure);
        cancelButton = findViewById(R.id.btn_cancel);
        acceptButton = findViewById(R.id.btn_accept);
        stopwatchTimeStart = System.currentTimeMillis();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        acceptButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                measure.setUserId(preferenceEditor.getUserId());
                if(measureText.getText().length() == 9) {
                    setResult(Activity.RESULT_OK);
                    finish();
                }
                else{
                    setResult(Activity.RESULT_CANCELED);
                    finish();
                }
            }
        });

        measureText.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable arg0) {
                switch(measureText.getText().toString()){
                    case "9":
                        stopwatchTimeStop = System.currentTimeMillis();
                        if(firstMeasure) {
                            measure.setDigit9(toIntExact(stopwatchTimeStop - stopwatchTimeStart));
                        }
                        break;
                    case "9R":
                        if(firstMeasure) {
                            measure.setR(toIntExact(stopwatchTimeStart - stopwatchTimeStop));
                            firstMeasure = false;
                        }
                        break;
                    case "9RJ":
                        if(!firstMeasure) {
                            measure.setJ(toIntExact(stopwatchTimeStop - stopwatchTimeStart));
                            firstMeasure = true;
                        }
                        break;
                    case "9RJh":
                        if(firstMeasure) {
                            measure.setH(toIntExact(stopwatchTimeStart - stopwatchTimeStop));
                            firstMeasure = false;
                        }
                        break;
                    case "9RJhl":
                        if(!firstMeasure) {
                            measure.setL(toIntExact(stopwatchTimeStop - stopwatchTimeStart));
                            firstMeasure = true;
                        }
                        break;
                    case "9RJhl6":
                        if(firstMeasure) {
                            measure.setDigit6(toIntExact(stopwatchTimeStart - stopwatchTimeStop));
                            firstMeasure = false;
                        }
                        break;
                    case "9RJhl6a":
                        if(!firstMeasure) {
                            measure.setA(toIntExact(stopwatchTimeStop - stopwatchTimeStart));
                            firstMeasure = true;
                        }
                        break;
                    case "9RJhl6a0":
                        if(firstMeasure) {
                            measure.setDigit0(toIntExact(stopwatchTimeStart - stopwatchTimeStop));
                            firstMeasure = false;
                        }
                        break;
                    case "9RJhl6a0n":
                        if(!firstMeasure) {
                            measure.setN(toIntExact(stopwatchTimeStop - stopwatchTimeStart));
                            firstMeasure = true;
                        }
                        break;
                    default:
                        arg0.clear();
                        measureText.getText().clear();
                        firstMeasure = true;
                        break;
                }
                return;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(firstMeasure) {
                    stopwatchTimeStart = System.currentTimeMillis();
                }
                if(!firstMeasure) {
                    stopwatchTimeStop = System.currentTimeMillis();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    private boolean firstMeasure = true;
    private long stopwatchTimeStart;
    private long stopwatchTimeStop;
    private Measure measure;
    private EditText measureText;
    private Button cancelButton;
    private Button acceptButton;
    private PreferenceEditor preferenceEditor;
}
