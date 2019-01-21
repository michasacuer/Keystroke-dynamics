package com.example.admin.keystroke_dynamics.DTO;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey
    @ColumnInfo(name = "Username")
    private String username;

    @ColumnInfo(name = "Email")
    private String email;

    @ColumnInfo(name = "Password")
    private String password;

    public String getUsername() {return this.username;}
    public void setUsername(String username) {this.username = username;}

    public String getEmail() {return this.email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return this.password;}
    public void setPassword(String password) {this.password = password;}

}
