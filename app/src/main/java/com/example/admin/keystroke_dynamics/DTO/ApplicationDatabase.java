package com.example.admin.keystroke_dynamics.DTO;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.admin.keystroke_dynamics.DTO.Measure.MeasureDao;
import com.example.admin.keystroke_dynamics.DTO.User.User;
import com.example.admin.keystroke_dynamics.DTO.User.UserDao;

@Database(entities = {User.class}, version = 2)
public abstract class ApplicationDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract MeasureDao measureDao();

    public static ApplicationDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ApplicationDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabase.class, "database").build();
                }
            }
        }
        return INSTANCE;
    }

    private static volatile ApplicationDatabase INSTANCE;
}
