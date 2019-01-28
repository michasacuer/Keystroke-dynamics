package com.example.admin.keystroke_dynamics.DTO.Measure;

import java.util.ArrayList;
import java.util.List;

public class MeasureWrapper {

    public int id;
    public int userId;
    public List<Integer> times = new ArrayList<>();

    public MeasureWrapper(Measure measure){
        this.id = measure.getId();
        this.userId = measure.getUserId();
        times.add(measure.getDigit9());
        times.add(measure.getR());
        times.add(measure.getJ());
        times.add(measure.getH());
        times.add(measure.getL());
        times.add(measure.getDigit6());
        times.add(measure.getA());
        times.add(measure.getDigit0());
        times.add(measure.getN());
    }
}
