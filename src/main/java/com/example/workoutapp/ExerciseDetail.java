package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class ExerciseDetail extends AppCompatActivity {

    private TextView textViewTitle, textViewTimer;
    private Button buttonStartStop;
    private Handler handler = new Handler();
    private Runnable timerRunnable;
    private boolean isRunning = false;
    private long startTime;
    private long elapsedTime = 0;
    private long timeInSeconds = 0; // Timer time in seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewTimer = findViewById(R.id.textViewTimer);
        buttonStartStop = findViewById(R.id.buttonStartStop);

        // Get the exercise name from the Intent
        String exerciseName = getIntent().getStringExtra("exerciseName");
        textViewTitle.setText(exerciseName);

        buttonStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    stopTimer();
                } else {
                    startTimer();
                }
            }
        });
    }

    private void startTimer() {
        startTime = System.currentTimeMillis();
        isRunning = true;
        buttonStartStop.setText("Stop");
        Snackbar.make(findViewById(android.R.id.content), "Timer Started", Snackbar.LENGTH_SHORT).show();

        timerRunnable = new Runnable() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                elapsedTime = currentTime - startTime + timeInSeconds * 1000;
                updateTimerDisplay();
                handler.postDelayed(this, 1000);
            }
        };

        handler.post(timerRunnable);
    }

    private void stopTimer() {
        isRunning = false;
        buttonStartStop.setText("Start");
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Timer Stopped", Snackbar.LENGTH_LONG);
        snackbar.setAction("Got It", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to WorkoutActivity
                Intent intent = new Intent(ExerciseDetail.this, WorkoutActivity.class);
                startActivity(intent);
                finish(); // Optionally finish the current activity
            }
        });
        snackbar.show();

        handler.removeCallbacks(timerRunnable);
        timeInSeconds = elapsedTime / 1000; // Save elapsed time in seconds
    }

    private void updateTimerDisplay() {
        long seconds = (elapsedTime / 1000) % 60;
        long minutes = (elapsedTime / (1000 * 60)) % 60;
        long hours = (elapsedTime / (1000 * 60 * 60)) % 24;

        textViewTimer.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }
}
