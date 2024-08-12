package com.example.workouttracker2.model;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Workout.class, Exercise.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase instance;
    private static final int NUMBER_OF_THREADS = 4; // Number of threads in the pool
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract WorkoutDao workoutDao();
    public abstract ExerciseDao exerciseDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "workout_database"
                            ).fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}
