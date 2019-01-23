package com.example.admin.keystroke_dynamics;

import android.content.Context;
import android.os.AsyncTask;

import com.example.admin.keystroke_dynamics.DTO.ApplicationDatabase;
import com.example.admin.keystroke_dynamics.DTO.User.User;

public class Signup extends AsyncTask<String, Integer, Long> {

    public Signup(Context context){
        db = ApplicationDatabase.getDatabase(context);
    }

    protected Long doInBackground(String... body){
        try {
            try{
                db.userDao().insertUser(new User(body[0], body[1], body[2]));
            } catch(Exception e){
                return null;
            }
            MailSender sender = new MailSender("michasacuer3@gmail.com", "pass");
            sender.sendMail(body[4], body[3], body[1], body[1]);
        } catch(Exception e) { }
    return null;
    }

    private ApplicationDatabase db;
}
