package com.example.admin.keystroke_dynamics.Signup;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.admin.keystroke_dynamics.Activities.SignupActivity;
import com.example.admin.keystroke_dynamics.DTO.ApplicationDatabase;
import com.example.admin.keystroke_dynamics.DTO.User.User;
import com.example.admin.keystroke_dynamics.R;

public class Signup extends AsyncTask<String, Boolean, Boolean> {

    public Signup(SignupActivity signupActivity, SignupListener listener){
        db = ApplicationDatabase.getDatabase(signupActivity);
        this.signupActivity = signupActivity;
        this.listener = listener;
    }

    public Signup(Signup signup){
        db = signup.db;
        signupActivity = signup.signupActivity;
        listener = signup.listener;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(signupActivity, R.style.AppTheme_Dark_Dialog);
        this.dialog.setMessage(signupActivity.getResources().getString(R.string.communicate));
        this.dialog.show();
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
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private ApplicationDatabase db;
    private SignupListener listener;
    private SignupActivity signupActivity;
    private User user;
    private ProgressDialog dialog;
}
