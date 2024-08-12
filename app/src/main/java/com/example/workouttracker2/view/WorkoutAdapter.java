package com.example.workouttracker2.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.workouttracker2.R;
import com.example.workouttracker2.model.Workout;
import com.example.workouttracker2.viewmodel.WorkoutViewModel;

import java.util.ArrayList;
import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {

    private List<Workout> workouts;
    private final WorkoutViewModel viewModel;

    public WorkoutAdapter(WorkoutViewModel viewModel) {
        this.viewModel = viewModel;
        this.workouts = new ArrayList<>();
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_workout, parent, false);
        return new WorkoutViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        Workout currentWorkout = workouts.get(position);
        holder.textViewWorkoutName.setText(currentWorkout.getName());
        holder.buttonDelete.setOnClickListener(v -> viewModel.delete(currentWorkout));
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
        notifyDataSetChanged();
    }

    class WorkoutViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewWorkoutName;
        Button buttonDelete;

        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWorkoutName = itemView.findViewById(R.id.text_view_workout_name);
            buttonDelete = itemView.findViewById(R.id.button_delete);
        }
    }
}