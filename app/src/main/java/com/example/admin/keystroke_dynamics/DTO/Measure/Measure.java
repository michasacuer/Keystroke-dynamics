package com.example.admin.keystroke_dynamics.DTO.Measure;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.example.admin.keystroke_dynamics.DTO.User.User;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "measures", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "username", childColumns = "userId", onDelete = CASCADE))
public class Measure {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int userId;
}
