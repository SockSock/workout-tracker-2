package com.example.workouttracker2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker2.R;
import com.example.workouttracker2.model.Exercise;
import com.example.workouttracker2.viewmodel.WorkoutViewModel;

import java.util.List;

public class ExerciseViewAdapter extends RecyclerView.Adapter<ExerciseViewAdapter.ExerciseViewHolder> {

    private List<Exercise> exerciseList;
    private final Context context;
    private final WorkoutViewModel viewModel;

    public ExerciseViewAdapter(Context context, WorkoutViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_exercise_view, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = exerciseList.get(position);

        holder.tvExerciseName.setText(exercise.getName());
        holder.tvSetsValue.setText(String.valueOf(exercise.getSets()));
        holder.tvRepsValue.setText(String.valueOf(exercise.getReps()));
        holder.tvWeightValue.setText(String.valueOf(exercise.getWeight()));

        holder.btnDelete.setOnClickListener(v -> {
            viewModel.delete(exercise);
            exerciseList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, exerciseList.size());
            Toast.makeText(context, "Exercise Deleted", Toast.LENGTH_SHORT).show();
        });

        holder.btnPlus.setOnClickListener(v -> {
            double newWeight = exercise.getWeight() + exercise.getIncrement();
            viewModel.updateExerciseWeight(exercise.getId(), newWeight);
            holder.tvWeightValue.setText(String.valueOf(newWeight));
        });

        holder.btnMinus.setOnClickListener(v -> {
            double newWeight = exercise.getWeight() - exercise.getIncrement();
            if (newWeight >= 0) {
                viewModel.updateExerciseWeight(exercise.getId(), newWeight);
                holder.tvWeightValue.setText(String.valueOf(newWeight));
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseList != null ? exerciseList.size() : 0;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exerciseList = exercises;
        notifyDataSetChanged();
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView tvExerciseName, tvSetsValue, tvRepsValue, tvWeightValue;
        Button btnDelete, btnPlus, btnMinus;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvExerciseName = itemView.findViewById(R.id.tvExerciseName);
            tvSetsValue = itemView.findViewById(R.id.tvSetsValue);
            tvRepsValue = itemView.findViewById(R.id.tvRepsValue);
            tvWeightValue = itemView.findViewById(R.id.tvWeightValue);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
        }
    }
}
