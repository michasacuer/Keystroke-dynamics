package com.example.admin.keystroke_dynamics.Login;

import android.content.Context;
import android.os.AsyncTask;

import com.example.admin.keystroke_dynamics.DTO.ApplicationDatabase;
import com.example.admin.keystroke_dynamics.DTO.User.User;


public class Login extends AsyncTask<String, Boolean, Boolean> {

    public Login(Context context, LoginListener listener){
        db = ApplicationDatabase.getDatabase(context);
        this.context = context;
        this.listener = listener;
    }

    public Login(Login login){
        db = login.db;
        context = login.context;
        listener = login.listener;
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
    }

    private ApplicationDatabase db;
    private User user;
    private LoginListener listener;
    private Context context;
    private LoggedUser loggedUser;
}
