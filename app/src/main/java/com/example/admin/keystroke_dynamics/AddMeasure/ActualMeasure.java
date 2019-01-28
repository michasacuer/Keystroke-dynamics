package com.example.admin.keystroke_dynamics.AddMeasure;

import com.example.admin.keystroke_dynamics.DTO.Measure.Measure;

public class ActualMeasure {
    public static Measure getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Measure();
        return INSTANCE;
    }

    private static Measure INSTANCE;
}
