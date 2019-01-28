package com.example.admin.keystroke_dynamics.DTO;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.admin.keystroke_dynamics.DTO.User.User;
import com.example.admin.keystroke_dynamics.DTO.User.UserDao;

import java.util.List;

public class UserRepository {

    public UserRepository(Context context){
        ApplicationDatabase db = ApplicationDatabase.getDatabase(context);
        userDao = db.userDao();
        allUsers = userDao.getAllLiveUsers();
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }

    private UserDao userDao;
    private LiveData<List<User>> allUsers;
}
