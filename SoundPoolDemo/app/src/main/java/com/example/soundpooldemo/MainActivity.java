package com.example.soundpooldemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AudioManager audioManager = new AudioManager(this);

        final TextView info = findViewById(R.id.info);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (audioManager.isReady()) {
                    Sound sound = getSound();
                    info.setText(sound.toString());
                    audioManager.play(sound);
                }
            }
        });
    }

    private Sound getSound() {
        Random random = new Random();
        int value = random.nextInt(Sound.values().length);
        switch (value) {
            case 0:
                return Sound.LAUGHING;
            case 1:
                return Sound.EXPLOSION;
            default:
                return Sound.WALKING;
        }
    }
}
