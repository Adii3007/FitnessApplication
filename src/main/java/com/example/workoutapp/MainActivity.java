package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonStartWorkout;
    private EditText editTextAge;
    private EditText editTextHeight;
    private EditText editTextWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStartWorkout = findViewById(R.id.buttonStartWorkout);
        editTextAge = findViewById(R.id.editTextAge);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);

        buttonStartWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String ageString = editTextAge.getText().toString();
                String heightString = editTextHeight.getText().toString();
                String weightString = editTextWeight.getText().toString();

                // Check if any field is empty
                if (ageString.isEmpty() || heightString.isEmpty() || weightString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all values to start the application", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Parse age and check if it's less than 15
                int age = Integer.parseInt(ageString);
                if (age < 15) {
                    Toast.makeText(MainActivity.this, "App is supported only for people above 15", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Proceed with starting the WorkoutActivity
                Intent intent = new Intent(MainActivity.this, WorkoutActivity.class);
                startActivity(intent);
            }
        });
    }
}
