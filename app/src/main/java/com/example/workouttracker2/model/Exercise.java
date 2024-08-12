package com.example.workouttracker2.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

@Entity
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int reps;
    private int sets;
    private double weight;
    private double increment;
    private int workoutId;

    public Exercise(String name, int reps, int sets, double weight, double increment, int workoutId) {
        this.name = name;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
        this.increment = increment;
        this.workoutId = workoutId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getIncrement() {
        return increment;
    }

    public void setIncrement(double increment) {
        this.increment = increment;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }
}
