package com.example.admin.keystroke_dynamics.Adapter;

import android.content.Context;

import com.example.admin.keystroke_dynamics.DTO.ApplicationDatabase;
import com.example.admin.keystroke_dynamics.DTO.Measure.Measure;
import com.example.admin.keystroke_dynamics.DTO.MeasureRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataMeasures {

    public ExpandableListDataMeasures(Context context, List<Measure> measures){
        this.context = context;
        this.measures = measures;
    }

    public HashMap<String, List<String>> getData() {

        for (Measure item: measures) {
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

            expandableListDetail.put("ID: " + item.getId(), measure);
        }
        return expandableListDetail;
    }

    private List<Measure> measures;
    private HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
    private Context context;
}