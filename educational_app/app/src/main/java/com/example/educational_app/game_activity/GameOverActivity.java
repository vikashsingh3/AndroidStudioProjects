package com.example.educational_app.game_activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.educational_app.R;
import com.example.educational_app.game_helper.Background;
import com.example.educational_app.game_helper.SqliteHelper;

import java.util.Locale;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class GameOverActivity extends AppCompatActivity {
    private int score;
    private Twitter twitter = TwitterFactory.getSingleton();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        String final_score = extras.getString("final_score");
        TextView label_score = findViewById(R.id.final_score);
        assert final_score != null;
        label_score.setText(final_score);
        add_Score_in_table(final_score);
    }

    public void btn_main_menu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void btn_Leaderboard(View view) {
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }

    private void add_Score_in_table(String final_score) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String player_name = pref.getString("player_name", "No Name");

        score = Integer.parseInt(final_score.substring(6).trim());

        SqliteHelper sqliteHelper = new SqliteHelper(this);
        sqliteHelper.insertHighScore(player_name, score);
    }

    public void btn_twitter(View view) {
        String message = "I have scored " + score + " on educational app. Can you beat me?";

        Background.run(() -> {
            if (isAuthorised()) {
                try {
                    twitter.updateStatus(message);
                    Log.i("GameOverActivity", String.format(Locale.getDefault(), "Status updated: %s", message));
                } catch (TwitterException e) {
                    Log.i("GameOverActivity", String.format(Locale.getDefault(),
                            "Something bad happened while tweeting: %s", e.toString()));
                }
            } else {
                Log.i("GameOverActivity", "Unable to update status. User not authorised. Directing to twitter");
                String url = "http://www.twitter.com/intent/tweet?text=" + message;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    private boolean isAuthorised() {
        try {
            twitter.verifyCredentials();
            Log.i("GameOverActivity", "User is verified");
            return true;
        } catch (Exception e) {
            Log.i("GameOverActivity", "User is not verified");
            return false;
        }
    }
}