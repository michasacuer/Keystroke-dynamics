package com.example.admin.keystroke_dynamics.DTO.User;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.admin.keystroke_dynamics.DTO.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    public UserViewModel(Application application){
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {return allUsers;}

    private UserRepository userRepository;
    private LiveData<List<User>> allUsers;
}
