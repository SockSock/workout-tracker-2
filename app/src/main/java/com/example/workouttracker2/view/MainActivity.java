package com.example.workouttracker2.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.workouttracker2.R;
import com.example.workouttracker2.model.Workout;
import com.example.workouttracker2.viewmodel.WorkoutViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.app.AlertDialog;
import android.content.DialogInterface;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WorkoutViewModel workoutViewModel;
    private RecyclerView recyclerView;
    private WorkoutAdapter workoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view_workouts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        workoutViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);

        workoutAdapter = new WorkoutAdapter(workoutViewModel);
        recyclerView.setAdapter(workoutAdapter);

        FloatingActionButton newWorkoutButton = findViewById(R.id.new_workout_button);
        newWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNewWorkoutDialog();
            }
        });

        workoutViewModel.getAllWorkouts().observe(this, new Observer<List<Workout>>() {
            @Override
            public void onChanged(List<Workout> workouts) {
                workoutAdapter.setWorkouts(workouts);
            }
        });
    }

    private void showNewWorkoutDialog() {
        final EditText editText = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Workout");
        builder.setView(editText);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String workoutName = editText.getText().toString().trim();

                if (!workoutName.isEmpty()) {
                    Workout workout = new Workout(workoutName);
                    workoutViewModel.insert(workout);
                } else {
                    Workout workout = new Workout("New Workout");
                    workoutViewModel.insert(workout);
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.create().show();
    }
}
