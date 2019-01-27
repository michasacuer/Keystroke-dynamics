package com.example.admin.keystroke_dynamics.Login;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.admin.keystroke_dynamics.Activities.LoginActivity;
import com.example.admin.keystroke_dynamics.DTO.ApplicationDatabase;
import com.example.admin.keystroke_dynamics.DTO.User.User;
import com.example.admin.keystroke_dynamics.R;


public class Login extends AsyncTask<String, Boolean, Boolean> {

    public Login(LoginActivity loginActivity, LoginListener listener){
        db = ApplicationDatabase.getDatabase(loginActivity);
        this.loginActivity = loginActivity;
        this.listener = listener;
    }

    public Login(Login login){
        db = login.db;
        loginActivity = login.loginActivity;
        listener = login.listener;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(loginActivity, R.style.AppTheme_Dark_Dialog);
        this.dialog.setMessage(loginActivity.getResources().getString(R.string.communicate));
        this.dialog.show();
    }

    @Override
    protected Boolean doInBackground(String... body){
        try {
            user = db.userDao().getUser(body[0], body[1]);
            if (user != null) {
                loggedUser = loggedUser.getInstance();
                loggedUser.setId(user.getId());
                loggedUser.setEmail(user.getEmail());
                loggedUser.setUsername(user.getUsername());
                return Boolean.TRUE;
            }
            else {
                return Boolean.FALSE;
            }
        }
        catch(Exception e){
            return null;
        }
    }

    protected void onPostExecute(Boolean result) {
        listener.onLoginPerformed(result);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private ApplicationDatabase db;
    private User user;
    private LoginListener listener;
    private LoginActivity loginActivity;
    private LoggedUser loggedUser;
    private ProgressDialog dialog;
}
