package com.example.admin.keystroke_dynamics.DTO;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.example.admin.keystroke_dynamics.DTO.Measure.Measure;
import com.example.admin.keystroke_dynamics.DTO.Measure.MeasureDao;
import com.example.admin.keystroke_dynamics.DTO.User.User;
import com.example.admin.keystroke_dynamics.DTO.User.UserDao;

import java.util.List;

public class MeasureRepository {

    public MeasureRepository(Context context){
        ApplicationDatabase db = ApplicationDatabase.getDatabase(context);
        measureDao = db.measureDao();
        allMeasures = measureDao.getAllMeasures();
    }

    public LiveData<List<Measure>> getAllMeasures() {
        return allMeasures;
    }

    public void insert(Measure measure){
        new insertAsyncTask(measureDao).execute(measure);
    }

    private static class insertAsyncTask extends AsyncTask<Measure, Void, Void>{

        private MeasureDao asyncTaskDao;

        insertAsyncTask(MeasureDao dao){
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Measure... params){
            asyncTaskDao.insertMeasure(params[0]);
            return null;
        }
    }

    private MeasureDao measureDao;
    private LiveData<List<Measure>> allMeasures;
}
