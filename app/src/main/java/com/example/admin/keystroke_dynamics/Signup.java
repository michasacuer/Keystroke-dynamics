package com.example.admin.keystroke_dynamics;

import android.os.AsyncTask;
import android.content.res.Resources;
import android.util.Log;

public class Signup extends AsyncTask<String, Integer, Long> {

    protected Long doInBackground(String... body){
        try {
            Log.d("TAG", body[1] + body[0] + body[2] + body[3]);
            MailSender sender = new MailSender("michasacuer3@gmail.com", "michasacuer");
            sender.sendMail(body[4], body[3], body[1], body[1]);
        }
        catch(Exception e) { }
    return null;
    }

}
