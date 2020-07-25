package com.example.educational_app.game_activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.educational_app.R;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer background_audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background_audio = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
    }

    public void btn_Options(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void btn_Leaderboard(View view) {
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean music_on = pref.getBoolean("music_setting", true);
        int music_vol = pref.getInt("music_volume", 100);

        if (music_on) {
            //Set the music
            background_audio.setVolume(music_vol, music_vol);
            if (background_audio.isPlaying()) {
                background_audio.stop();
            }
            float MAX_VOLUME = 100;
            float music_volume = (float) (1 - (Math.log(MAX_VOLUME - music_vol) / Math.log(MAX_VOLUME)));
            background_audio = MediaPlayer.create(this, R.raw.disco_beat);
            background_audio.setVolume(music_volume, music_volume);
            background_audio.setLooping(true);
            background_audio.start();
        } else {
            background_audio.stop();
        }
    }

    public void btn_Play(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
