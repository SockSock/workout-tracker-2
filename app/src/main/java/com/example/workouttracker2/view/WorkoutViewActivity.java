package com.example.workouttracker2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.workouttracker2.R;
import com.example.workouttracker2.model.Exercise;
import com.example.workouttracker2.viewmodel.WorkoutViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class WorkoutViewActivity extends AppCompatActivity {

    private WorkoutViewModel workoutViewModel;
    private RecyclerView recyclerView;
    private ExerciseViewAdapter exerciseViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_view);

        int workoutId = getIntent().getIntExtra("WORKOUT_ID", -1);

        workoutViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);

        recyclerView = findViewById(R.id.recycler_view_exercise_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        exerciseViewAdapter = new ExerciseViewAdapter(this, workoutViewModel);
        recyclerView.setAdapter(exerciseViewAdapter);

        workoutViewModel.getExercisesForWorkout(workoutId).observe(this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                exerciseViewAdapter.setExercises(exercises);
            }
        });

        FloatingActionButton newExerciseButton = findViewById(R.id.new_exercise_button);
        newExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutViewActivity.this, ChooseExerciseActivity.class);
                intent.putExtra("WORKOUT_ID", workoutId);
                startActivity(intent);
            }
        });
    }
}
