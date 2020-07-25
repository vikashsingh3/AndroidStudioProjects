package com.example.foleyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foleyapp.Enums.EXTRAS;
import com.example.foleyapp.Enums.HUMAN;
import com.example.foleyapp.Enums.NATURE;
import com.example.foleyapp.Enums.VEHICLE;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SoundManager soundManager;
    private TextView textView_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soundManager = new SoundManager(this);
        textView_message = findViewById(R.id.textView_message);
    }

    private HUMAN getHumanSound() {
        Random random = new Random();
        int value = random.nextInt(HUMAN.values().length);
        switch (value) {
            case 0:
                return HUMAN.SOUND01;
            case 1:
                return HUMAN.SOUND02;
            case 2:
                return HUMAN.SOUND03;
            default:
                return HUMAN.SOUND04;
        }
    }

    private NATURE getNatureSound() {
        Random random = new Random();
        int value = random.nextInt(NATURE.values().length);
        switch (value) {
            case 0:
                return NATURE.SOUND01;
            case 1:
                return NATURE.SOUND02;
            case 2:
                return NATURE.SOUND03;
            case 3:
                return NATURE.SOUND04;
            case 4:
                return NATURE.SOUND05;
            case 5:
                return NATURE.SOUND06;
            case 6:
                return NATURE.SOUND07;
            default:
                return NATURE.SOUND08;
        }
    }

    public EXTRAS getExtraSound() {
        Random random = new Random();
        int value = random.nextInt(EXTRAS.values().length);
        switch (value) {
            case 0:
                return EXTRAS.SOUND01;
            case 1:
                return EXTRAS.SOUND02;
            case 2:
                return EXTRAS.SOUND03;
            case 3:
                return EXTRAS.SOUND04;
            case 4:
                return EXTRAS.SOUND05;
            default:
                return EXTRAS.SOUND06;
        }
    }

    private VEHICLE getVehicleSound() {
        Random random = new Random();
        int value = random.nextInt(VEHICLE.values().length);
        switch (value) {
            case 0:
                return VEHICLE.SOUND01;
            case 1:
                return VEHICLE.SOUND02;
            case 2:
                return VEHICLE.SOUND03;
            case 3:
                return VEHICLE.SOUND04;
            case 4:
                return VEHICLE.SOUND05;
            case 5:
                return VEHICLE.SOUND06;
            case 6:
                return VEHICLE.SOUND07;
            case 7:
                return VEHICLE.SOUND08;
            default:
                return VEHICLE.SOUND09;
        }
    }

    public void button_click(View view) {
        Button b = (Button) view;
        String buttonText;
        buttonText = b.getText().toString();
        Intent intent = new Intent(getBaseContext(), SecondaryActivity.class);
        if (soundManager.isReady()) {
            if (view.getId() == R.id.button_extra) {
                EXTRAS sound = getExtraSound();
                textView_message.setText(sound.toString());
                soundManager.play_extra(sound);
            } else if (view.getId() == R.id.button_human) {
                HUMAN sound = getHumanSound();
                textView_message.setText(sound.toString());
                soundManager.play_human(sound);
            } else if (view.getId() == R.id.button_vehicle) {
                VEHICLE sound = getVehicleSound();
                textView_message.setText(sound.toString());
                soundManager.play_vehicle(sound);
            } else if (view.getId() == R.id.button_nature) {
                NATURE sound = getNatureSound();
                textView_message.setText(sound.toString());
                soundManager.play_nature(sound);
            }
            intent.putExtra("buttonName", buttonText);
            startActivity(intent);
        }
    }
}
