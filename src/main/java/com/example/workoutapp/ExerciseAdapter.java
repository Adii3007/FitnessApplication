package com.example.workoutapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public class ExerciseAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Exercise> exercises;

    public ExerciseAdapter(Context context, ArrayList<Exercise> exercises) {
        this.context = context;
        this.exercises = exercises;
    }

    @Override
    public int getCount() {
        return exercises.size();
    }

    @Override
    public Object getItem(int position) {
        return exercises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.exercise_list_item, parent, false);
        }

        Exercise exercise = exercises.get(position);

        TextView textView = convertView.findViewById(R.id.textViewExerciseName);

        textView.setText(exercise.getName());

        return convertView;
    }
}
