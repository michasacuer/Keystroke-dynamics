package com.example.admin.keystroke_dynamics.Signup;

import android.content.Context;
import android.os.AsyncTask;

import com.example.admin.keystroke_dynamics.DTO.ApplicationDatabase;
import com.example.admin.keystroke_dynamics.DTO.User.User;

public class Signup extends AsyncTask<String, Boolean, Boolean> {

    public Signup(Context context, SignupListener listener){
        db = ApplicationDatabase.getDatabase(context);
        this.context = context;
        this.listener = listener;
    }

    public Signup(Signup signup){
        db = signup.db;
        context = signup.context;
        listener = signup.listener;
    }

    @Override
    protected void onPreExecute() {
    }

    protected Boolean doInBackground(String... body){
        try {
            try {
                user = db.userDao().getUser(body[0], body[1], body[2]);
                if (user == null) {
                    db.userDao().insertUser(new User(body[0], body[1], body[2]));
                } else {
                    return Boolean.FALSE;
                }
            } catch(Exception e){
                throw new Exception();
            }
            MailSender sender = new MailSender("michasacuer3@gmail.com", "pass");
            sender.sendMail(body[4], body[3], body[1], body[1]);
            return Boolean.TRUE;
        } catch(Exception e) { }
            return null;
    }

    protected void onPostExecute(Boolean result) {
        listener.onSignupPerformed(result);
    }

    private ApplicationDatabase db;
    private SignupListener listener;
    private Context context;
    private User user;
}
