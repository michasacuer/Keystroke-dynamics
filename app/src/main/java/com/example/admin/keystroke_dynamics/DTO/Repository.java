package com.example.admin.keystroke_dynamics.DTO;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class Repository {

    Repository(Application application){
        ApplicationDatabase context = ApplicationDatabase.getDatabase(application);
        userDao = context.userDao();
        allUsers = userDao.getAllUsers();
    }

    LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insert(User user){
        new insertAsyncTask(userDao).execute(user);
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
    private LiveData<List<User>> allUsers;
}
