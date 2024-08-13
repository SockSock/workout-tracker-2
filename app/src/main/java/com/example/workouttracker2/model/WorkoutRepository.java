package com.example.workouttracker2.model;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WorkoutRepository {
    private WorkoutDao workoutDao;
    private ExerciseDao exerciseDao;
    private LiveData<List<Workout>> allWorkouts;

    public WorkoutRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        workoutDao = db.workoutDao();
        exerciseDao = db.exerciseDao();
        allWorkouts = workoutDao.getAllWorkouts();
    }

    public void insert(Workout workout) {
        AppDatabase.databaseWriteExecutor.execute(() -> workoutDao.insert(workout));
    }

    public void insert(Exercise exercise) {
        AppDatabase.databaseWriteExecutor.execute(() -> exerciseDao.insert(exercise));
    }

    public void delete(Workout workout) {
        AppDatabase.databaseWriteExecutor.execute(() -> workoutDao.delete(workout));
    }

    public void delete(Exercise exercise) {
        AppDatabase.databaseWriteExecutor.execute(() -> exerciseDao.delete(exercise));
    }

    public void updateExerciseWeight(int exerciseId, double newWeight) {
        AppDatabase.databaseWriteExecutor.execute(() -> exerciseDao.updateExerciseWeight(exerciseId, newWeight));
    }

    public LiveData<List<Workout>> getAllWorkouts() {
        return allWorkouts;
    }

    public LiveData<List<Exercise>> getExercisesForWorkout(int workoutId) {
        return exerciseDao.getExercisesForWorkout(workoutId);
    }
}
