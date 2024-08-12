package com.example.workouttracker2.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.workouttracker2.R;
import com.example.workouttracker2.model.Exercise;
import com.example.workouttracker2.model.Workout;
import com.example.workouttracker2.viewmodel.WorkoutViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WorkoutViewModel workoutViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        workoutViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);

        workoutViewModel.getAllWorkouts().observe(this, new Observer<List<Workout>>() {
            @Override
            public void onChanged(List<Workout> workouts) {
                // Update UI with the list of workouts
            }
        });
    }
}