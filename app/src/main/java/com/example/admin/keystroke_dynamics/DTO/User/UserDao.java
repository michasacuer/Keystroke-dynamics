package com.example.admin.keystroke_dynamics.DTO.User;

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

    @Query("SELECT * FROM users WHERE email = :email AND password = :password ")
    User getUser(String email, String password);

    @Query("SELECT * FROM users WHERE id = :id")
    User getUser(int id);

    @Query("Select * FROM users")
    List<User> getAllUsers();
}
