package com.example.educational_app.game_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.educational_app.R;
import com.example.educational_app.game_activity.GameOverActivity;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {
    private int seconds = 45;
    private boolean running;
    private TextView question_number;
    private TextView score;
    private TextView timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        question_number = view.findViewById(R.id.label_question);
        score = view.findViewById(R.id.label_score);
        timer = view.findViewById(R.id.label_timer);
        running = true;
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            setScore(savedInstanceState.getString("score_value"));
            setQuestion_number(savedInstanceState.getString("question_number_value"));
        }
        runTimer();
        return view;
    }

    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int minutes = seconds / 60;
                int secs = seconds % 60;

                if (running) {
                    String time = String.format(Locale.getDefault(),
                            "%02d:%02d", minutes, secs);
                    timer.setText(time);
                    seconds = seconds - 1;
                }
                if (seconds == -1) {
                    running = false;
                    completeGame();
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void completeGame() {
        running = false;
        String time = String.format(Locale.getDefault(),
                "%02d:%02d", 0, 0);
        timer.setText(time);
        seconds = -99;
        Intent intent = new Intent(getActivity(), GameOverActivity.class);
        intent.putExtra("final_score", score.getText());
        startActivity(intent);
    }

    public void setQuestion_number(String text) {
        question_number.setText(text);
    }

    public void setScore(String text) {
        score.setText(text);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putString("score_value", (String) score.getText());
        savedInstanceState.putString("question_number_value", (String) question_number.getText());
    }
}
