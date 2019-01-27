package com.example.admin.keystroke_dynamics.DTO.User;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "users")
public class User {

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {}

    @ColumnInfo(name = "Id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "Username")
    private String username;

    @ColumnInfo(name = "Email")
    private String email;

    @ColumnInfo(name = "Password")
    private String password;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getUsername() {return this.username;}
    public void setUsername(String username) {this.username = username;}

    public String getEmail() {return this.email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return this.password;}
    public void setPassword(String password) {this.password = password;}

}
