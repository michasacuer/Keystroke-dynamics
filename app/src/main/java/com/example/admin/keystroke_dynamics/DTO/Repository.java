package com.example.admin.keystroke_dynamics.DTO;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.example.admin.keystroke_dynamics.DTO.User.User;
import com.example.admin.keystroke_dynamics.DTO.User.UserDao;

import java.util.List;

public class Repository {

    public Repository(Context context){
        ApplicationDatabase db = ApplicationDatabase.getDatabase(context);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void insert(User user){
        new insertAsyncTask(userDao).execute(user);
    }

    public User getUser(String email, String password) {
        for(User user : allUsers){
            if(email == user.getEmail() && password == user.getPassword()){
                return user;
            }
        }
        return null;
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao asyncTaskDao;

        insertAsyncTask(UserDao dao){
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params){
            asyncTaskDao.insertUser(params[0]);
            return null;
        }
    }

    private UserDao userDao;
    private List<User> allUsers;
}
