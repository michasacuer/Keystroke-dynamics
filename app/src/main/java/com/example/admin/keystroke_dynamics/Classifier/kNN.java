package com.example.admin.keystroke_dynamics.Classifier;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.view.ContextThemeWrapper;

import com.example.admin.keystroke_dynamics.Activities.InfoDialog;
import com.example.admin.keystroke_dynamics.DTO.Measure.Measure;
import com.example.admin.keystroke_dynamics.DTO.Measure.MeasureWrapper;
import com.example.admin.keystroke_dynamics.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class kNN {

    public int execute(Measure measure, List<Measure> measures){

        measureWrapper = new MeasureWrapper(measure);

        for(Measure item: measures){
            MeasureWrapper current = new MeasureWrapper(item);
            for(int i = 0; i < current.times.size(); i++){
                result += Math.abs(measureWrapper.times.get(i) - current.times.get(i));
            }
            classifiers.add(new Classifier(result, item.getUserId()));
            result = 0;
        }

        classifiers.sort(new Comparator<Classifier>() {
            @Override
            public int compare(Classifier o1, Classifier o2) {
                return o1.getResult() - o2.getResult();
            }
        });

        return classifiers.get(0).getUserId();

    }

    private MeasureWrapper measureWrapper;
    private int result = 0;
    private static int k = 1; //k param
    private List<Classifier> classifiers = new ArrayList<>();

    private class Classifier {

        private Classifier(int result, int userId){
            this.result = result;
            this.userId = userId;
        }

        private void setResult(int result) { this.result = result; }
        private int getResult() {return result; }

        private void setUserId(int userId) { this.userId = userId; }
        private int getUserId() { return userId; }

        private int result;
        private int userId;
    }
}
