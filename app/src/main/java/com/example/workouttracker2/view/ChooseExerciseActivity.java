package com.example.workouttracker2.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.workouttracker2.R;
import com.example.workouttracker2.viewmodel.WorkoutViewModel;

import java.util.Arrays;
import java.util.List;

public class ChooseExerciseActivity extends AppCompatActivity {

    private RecyclerView recyclerViewExercises;
    private ChooseExerciseAdapter adapter;
    private WorkoutViewModel workoutViewModel;
    private int workoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exercise);

        recyclerViewExercises = findViewById(R.id.recycler_view_choose_exercise);
        recyclerViewExercises.setLayoutManager(new LinearLayoutManager(this));

        workoutViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);

        workoutId = getIntent().getIntExtra("WORKOUT_ID", -1);

        adapter = new ChooseExerciseAdapter(workoutViewModel, workoutId);
        recyclerViewExercises.setAdapter(adapter);

        List<String> predefinedExerciseNames = Arrays.asList(
                "Bench Press",
                "Squat",
                "Deadlift",
                "Overhead Press",
                "Cable Row"
        );

        adapter.setExerciseNames(predefinedExerciseNames);
    }
}
