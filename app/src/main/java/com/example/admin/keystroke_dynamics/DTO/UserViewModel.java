package com.example.admin.keystroke_dynamics.DTO;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    public UserViewModel(Application application){
        super(application);
        repository = new Repository(application);
        allUsers = repository.getAllUsers();
    }

    private Repository repository;
    private LiveData<List<User>> allUsers;
}