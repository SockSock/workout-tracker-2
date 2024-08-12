package com.example.workouttracker2.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface WorkoutDao {
    @Insert
    void insert(Workout workout);

    @Delete
    void delete(Workout workout);

    @Query("SELECT * FROM Workout")
    LiveData<List<Workout>> getAllWorkouts();
}
