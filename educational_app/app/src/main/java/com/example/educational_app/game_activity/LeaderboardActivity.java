package com.example.educational_app.game_activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.educational_app.R;
import com.example.educational_app.game_helper.SqliteHelper;
import com.example.educational_app.game_manager.HighScorePlayer;

import java.util.List;
import java.util.Objects;

public class LeaderboardActivity extends AppCompatActivity {
    private List<HighScorePlayer> playerList;
    private TextView textView_rank, textView_playerName, textView_score;
    private GridLayout gridLayout;

    private SensorManager sensorManager;
    private float accelerometer, current_accelerometer, last_accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        gridLayout = findViewById(R.id.leaderboard_layout);

        // Get the player names, score and rank from database
        SqliteHelper sqliteHelper = new SqliteHelper(this);
        playerList = sqliteHelper.getTopPlayer();

        updateLeaderBoard();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(sensorManager).registerListener(sensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        accelerometer = 10f;
        current_accelerometer = SensorManager.GRAVITY_EARTH;
        last_accelerometer = SensorManager.GRAVITY_EARTH;
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(sensorEventListener);
        super.onPause();
    }

    private void updateLeaderBoard() {
        int player_total_count = playerList.size();
        String rank, playerName, score;

        for (int player_number = -1; player_number < player_total_count; player_number++) {
            // Set the textView in layout
            setLayout();

            if (player_number == -1) {
                // Set the header
                textView_rank.setPadding(50, 20, 20, 20);
                textView_playerName.setPadding(50, 20, 30, 20);
                textView_score.setPadding(50, 20, 30, 20);

                rank = "Rank";
                playerName = "Player Name";
                score = "Score";
            } else {
                HighScorePlayer currentPlayer = playerList.get(player_number);

                rank = String.valueOf(currentPlayer.getRank());
                playerName = currentPlayer.getPlayerName();
                score = String.valueOf(currentPlayer.getScore());
            }
            textView_rank.setText(rank);
            textView_playerName.setText(playerName);
            textView_score.setText(score);

            gridLayout.addView(textView_rank);
            gridLayout.addView(textView_playerName);
            gridLayout.addView(textView_score);
        }
    }

    private void setLayout() {
        textView_rank = new TextView(this);
        textView_rank.setGravity(Gravity.CENTER_VERTICAL);
        textView_rank.setPadding(80, 20, 20, 20);
        textView_rank.setLayoutParams(new GridLayout.LayoutParams());
        textView_rank.setTextSize(20);

        textView_playerName = new TextView(this);
        textView_playerName.setGravity(Gravity.CENTER);
        textView_playerName.setPadding(50, 20, 30, 20);
        textView_playerName.setLayoutParams(new GridLayout.LayoutParams());
        textView_playerName.setTextSize(20);

        textView_score = new TextView(this);
        textView_score.setGravity(View.TEXT_ALIGNMENT_CENTER);
        textView_score.setPadding(80, 20, 30, 20);
        textView_score.setLayoutParams(new GridLayout.LayoutParams());
        textView_score.setTextSize(20);
    }

    public void btn_Close(View view) {
        finish();
    }

    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float difference;
            float acc_x = event.values[0];
            float acc_y = event.values[1];
            float acc_z = event.values[2];

            last_accelerometer = current_accelerometer;
            current_accelerometer = (float) Math.sqrt(acc_x * acc_x + acc_y * acc_y + acc_z * acc_z);

            difference = current_accelerometer - last_accelerometer;
            accelerometer = accelerometer * 0.9f + difference;

            if (accelerometer > 15) {
                //Go to landing page
                navigateUpTo(new Intent(getBaseContext(), MainActivity.class));
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
}
