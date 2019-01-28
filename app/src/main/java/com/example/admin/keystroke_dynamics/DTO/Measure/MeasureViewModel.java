package com.example.admin.keystroke_dynamics.DTO.Measure;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.admin.keystroke_dynamics.DTO.MeasureRepository;

import java.util.List;

public class MeasureViewModel extends AndroidViewModel {

    public MeasureViewModel(Application application){
        super(application);
        measureRepository = new MeasureRepository(application);
        allMeasures = measureRepository.getAllMeasures();
    }

    public LiveData<List<Measure>> getAllMeasures() {return allMeasures;}
    public void insert(Measure measure) { measureRepository.insert(measure);}

    private MeasureRepository measureRepository;

    private LiveData<List<Measure>> allMeasures;


}
