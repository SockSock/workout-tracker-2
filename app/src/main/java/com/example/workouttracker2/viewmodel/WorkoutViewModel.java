package com.example.workouttracker2.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.workouttracker2.model.Exercise;
import com.example.workouttracker2.model.Workout;
import com.example.workouttracker2.model.WorkoutRepository;

import java.util.List;

public class WorkoutViewModel extends AndroidViewModel {
    private WorkoutRepository repository;
    private LiveData<List<Workout>> allWorkouts;

    public WorkoutViewModel(Application application) {
        super(application);
        repository = new WorkoutRepository(application);
        allWorkouts = repository.getAllWorkouts();
    }

    public void insert(Workout workout) {
        repository.insert(workout);
    }

    public void insert(Exercise exercise) {
        repository.insert(exercise);
    }

    public LiveData<List<Workout>> getAllWorkouts() {
        return allWorkouts;
    }

    public List<Exercise> getExercisesForWorkout(int workoutId) {
        return repository.getExercisesForWorkout(workoutId);
    }
}
