package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ChestActivity extends AppCompatActivity {

    private ListView listViewExercises;
    private ArrayList<Exercise> exercisesList;
    private ExerciseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest);

        // Initialize the ListView
        listViewExercises = findViewById(R.id.listViewExercises);

        // Initialize the list of exercises
        exercisesList = new ArrayList<>();
        exercisesList.add(new Exercise("Push-Up"));
        exercisesList.add(new Exercise("Bench Press"));
        exercisesList.add(new Exercise("Chest Fly"));
        exercisesList.add(new Exercise("Incline Press"));
        exercisesList.add(new Exercise("Dumbbell Press"));
        exercisesList.add(new Exercise("Cable Crossover"));
        exercisesList.add(new Exercise("Parallel Bar Dips"));
        exercisesList.add(new Exercise("Decline Bench Press"));
        exercisesList.add(new Exercise("Dumbell Flyes"));
        exercisesList.add(new Exercise("Dumbbell Pullover"));

        // Set up the custom adapter
        adapter = new ExerciseAdapter(this, exercisesList);
        listViewExercises.setAdapter(adapter);

        // Set item click listener for the ListView
        listViewExercises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Exercise selectedExercise = exercisesList.get(position);
                Intent intent = new Intent(ChestActivity.this, ExerciseDetail.class);
                intent.putExtra("exerciseName", selectedExercise.getName());
                startActivity(intent);
            }
        });
    }
}
