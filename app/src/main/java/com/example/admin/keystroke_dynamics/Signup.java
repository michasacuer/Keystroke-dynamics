package com.example.admin.keystroke_dynamics;

import android.os.AsyncTask;
import android.content.res.Resources;

public class Signup extends AsyncTask<String, Integer, Long> {

    protected Long doInBackground(String... body){
        try {
            MailSender sender = new MailSender("michasacuer3@gmail.com", "michasacuer");
            sender.sendMail(body[4], body[3], body[1], body[1]);
        }
        catch(Exception e) { }
    return null;
    }

}
