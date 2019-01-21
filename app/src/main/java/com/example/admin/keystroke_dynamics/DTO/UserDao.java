package com.example.admin.keystroke_dynamics.DTO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE username = :username")
    LiveData<User> getUser(String username);

    @Query("Select * FROM users")
    LiveData<List<User>> getAllUsers();
}
