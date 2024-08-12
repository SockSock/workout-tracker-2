package com.example.workouttracker2.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Insert
    void insert(Exercise exercise);

    @Delete
    void delete(Exercise exercise);

    @Query("SELECT * FROM Exercise WHERE workoutId = :workoutId")
    List<Exercise> getExercisesForWorkout(int workoutId);
}
