package com.example.admin.keystroke_dynamics.DTO.Measure;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MeasureDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insertMeasure(Measure measure);

    @Query("SELECT * FROM measures")
    List<Measure> getAllMeasures();

    @Query("SELECT * FROM measures WHERE userId = :userId")
    List<Measure> getUserMeasures(int userId);

}
