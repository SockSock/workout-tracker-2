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

    public LiveData<List<Workout>> getAllWorkouts() {
        return allWorkouts;
    }

    public List<Exercise> getExercisesForWorkout(int workoutId) {
        return exerciseDao.getExercisesForWorkout(workoutId);
    }
}
