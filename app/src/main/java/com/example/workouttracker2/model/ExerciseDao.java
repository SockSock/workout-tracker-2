package com.example.workouttracker2.model;

import androidx.lifecycle.LiveData;
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
    LiveData<List<Exercise>> getExercisesForWorkout(int workoutId);

    @Query("UPDATE Exercise SET weight = :newWeight WHERE id = :exerciseId")
    void updateExerciseWeight(int exerciseId, double newWeight);
}
