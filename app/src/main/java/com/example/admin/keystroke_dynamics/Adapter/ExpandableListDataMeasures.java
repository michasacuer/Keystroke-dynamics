package com.example.admin.keystroke_dynamics.Adapter;

import android.content.Context;

import com.example.admin.keystroke_dynamics.DTO.ApplicationDatabase;
import com.example.admin.keystroke_dynamics.DTO.Measure.Measure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataMeasures {

    public ExpandableListDataMeasures(Context context){
        this.context = context;
    }

    public HashMap<String, List<String>> getData() {

        List<Measure> measures = getMeasures();

        //for (Measure item: measures) {
            List<String> measure = new ArrayList<String>();
            measure.add("9");
            measure.add("R");
            measure.add("J");
            measure.add("h");
            measure.add("l ");
            measure.add("6");
            measure.add("a");
            measure.add("0");
            measure.add("n");

            //expandableListDetail.put("ID: " + item.getId(), measure);
            expandableListDetail.put("ID: ", measure);
        //}

        return expandableListDetail;
        //return null;
    }

    private List<Measure> getMeasures(){
        db = ApplicationDatabase.getDatabase(context);
        return null;
    }

    private HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
    private ApplicationDatabase db;
    private Context context;
}