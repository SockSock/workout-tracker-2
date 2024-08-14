package com.example.workouttracker2.view;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker2.R;
import com.example.workouttracker2.model.Exercise;
import com.example.workouttracker2.viewmodel.WorkoutViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChooseExerciseAdapter extends RecyclerView.Adapter<ChooseExerciseAdapter.ChooseExerciseViewHolder> {

    private List<String> exerciseNames;
    private final WorkoutViewModel viewModel;
    private final int workoutId;

    public ChooseExerciseAdapter(WorkoutViewModel viewModel, int workoutId) {
        this.exerciseNames = new ArrayList<>();
        this.viewModel = viewModel;
        this.workoutId = workoutId;
    }

    @NonNull
    @Override
    public ChooseExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_choose_exercise, parent, false);
        return new ChooseExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseExerciseViewHolder holder, int position) {
        String exerciseName = exerciseNames.get(position);
        holder.textViewExerciseName.setText(exerciseName);

        holder.itemView.setOnClickListener(view -> showInputDialog(view.getContext(), exerciseName));
    }

    @Override
    public int getItemCount() {
        return exerciseNames.size();
    }

    public void setExerciseNames(List<String> exerciseNames) {
        this.exerciseNames = exerciseNames;
        notifyDataSetChanged();
    }

    private void showInputDialog(Context context, String exerciseName) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_set_exercise, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setView(dialogView);

        EditText editTextSets = dialogView.findViewById(R.id.editTextSets);
        EditText editTextReps = dialogView.findViewById(R.id.editTextReps);
        EditText editTextWeight = dialogView.findViewById(R.id.editTextWeight);
        EditText editTextIncrement = dialogView.findViewById(R.id.editTextIncrement);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);
        Button buttonOk = dialogView.findViewById(R.id.buttonOk);

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        buttonCancel.setOnClickListener(view -> dialog.dismiss());

        buttonOk.setOnClickListener(view -> {
            String setsInput = editTextSets.getText().toString().trim();
            String repsInput = editTextReps.getText().toString().trim();
            String weightInput = editTextWeight.getText().toString().trim();
            String incrementInput = editTextIncrement.getText().toString().trim();

            if (validateInputs(setsInput, repsInput, weightInput, incrementInput)) {
                int sets = Integer.parseInt(setsInput);
                int reps = Integer.parseInt(repsInput);
                double weight = Double.parseDouble(weightInput);
                double increment = Double.parseDouble(incrementInput);

                Exercise newExercise = new Exercise(exerciseName, reps, sets, weight, increment, workoutId);
                viewModel.insert(newExercise);

                dialog.dismiss();
            }
        });
    }

    private boolean validateInputs(String sets, String reps, String weight, String increment) {
        if (TextUtils.isEmpty(sets) || !TextUtils.isDigitsOnly(sets)) {
            showToast("Please enter a valid integer for Sets.");
            return false;
        }

        if (TextUtils.isEmpty(reps) || !TextUtils.isDigitsOnly(reps)) {
            showToast("Please enter a valid integer for Reps.");
            return false;
        }

        try {
            Double.parseDouble(weight);
        } catch (NumberFormatException e) {
            showToast("Please enter a valid number for Weight.");
            return false;
        }

        try {
            Double.parseDouble(increment);
        } catch (NumberFormatException e) {
            showToast("Please enter a valid number for Increment.");
            return false;
        }

        return true;
    }

    private void showToast(String message) {
        Toast.makeText(viewModel.getApplication(), message, Toast.LENGTH_SHORT).show();
    }

    static class ChooseExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView textViewExerciseName;

        public ChooseExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewExerciseName = itemView.findViewById(R.id.text_view_workout_name);
        }
    }
}
