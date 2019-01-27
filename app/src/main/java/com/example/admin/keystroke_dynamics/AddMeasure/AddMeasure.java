package com.example.admin.keystroke_dynamics.AddMeasure;

import android.content.Context;
import android.os.AsyncTask;

import com.example.admin.keystroke_dynamics.DTO.ApplicationDatabase;
import com.example.admin.keystroke_dynamics.Login.LoggedUser;

public class AddMeasure extends AsyncTask<String, Boolean, Boolean> {

    public AddMeasure(Context context){
        this.context = context;
        db = ApplicationDatabase.getDatabase(context);
    }

    @Override
    protected Boolean doInBackground(String... body){
        try {
            if(loggedUser != null) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }
        catch(Exception e){
            return null;
        }
    }

    private Context context;
    private LoggedUser loggedUser;
    private ApplicationDatabase db;
}
