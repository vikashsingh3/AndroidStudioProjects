package com.example.foleyapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foleyapp.Enums.EXTRAS;
import com.example.foleyapp.Enums.HUMAN;
import com.example.foleyapp.Enums.NATURE;
import com.example.foleyapp.Enums.VEHICLE;

import java.io.IOException;
import java.io.InputStream;

public class SecondaryActivity extends AppCompatActivity {
    private String item_selected;
    private int screenSizeX, screenSizeY;
    private Rect r1, r2, r3, r4;
    SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        Intent intent = getIntent();
        item_selected = intent.getStringExtra("buttonName");
        soundManager = new SoundManager(this);

        loadImage();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (r1.contains((int) event.getX(), (int) event.getY())) {
            play_sound(0);
        } else if (r2.contains((int) event.getX(), (int) event.getY())) {
            play_sound(1);
        } else if (r3.contains((int) event.getX(), (int) event.getY())) {
            play_sound(2);
        } else if (r4.contains((int) event.getX(), (int) event.getY())) {
            play_sound(3);
        }
        return true;
    }

    public void button_back(View view) {
        finish();
    }

    public void loadImage() {
        ImageView imageView = findViewById(R.id.imageView);

        assert item_selected != null;
        String imageName;
        switch (item_selected) {
            case "Nature":
                imageName = "nature.jpg";
                break;
            case "Extras":
                imageName = "extra.jpg";
                break;
            case "Human":
                imageName = "people.jpeg";
                break;
            case "Vehicle":
                imageName = "porsche.jpg";
                break;
            default:
                imageName = "animal.jpg";
                break;
        }

        AssetManager assetManager = getAssets();
        try {
            InputStream stream = assetManager.open("images/" + imageName);
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            Log.i("Secondary Activity", "Unable to open image: " + "images/" + imageName);
        }

        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                screenSizeX = imageView.getWidth();
                screenSizeY = imageView.getHeight();
                Log.i("Secondary screen", "Layout size: X: " + screenSizeX + ", Y: " + screenSizeY);

                drawRectangle(screenSizeX, screenSizeY);
            }
        });
    }

    private void drawRectangle(int screenSizeX, int screenSizeY) {
        int firstRecX = screenSizeX / 2;
        int firstRecY = screenSizeY / 2;
        r1 = new Rect(10, 100, firstRecX, firstRecY);
        r2 = new Rect(firstRecX, 100, screenSizeX, firstRecY);
        r3 = new Rect(10, firstRecY, firstRecX, screenSizeY);
        r4 = new Rect(firstRecX, firstRecY, screenSizeX, screenSizeY);
    }

    public void play_sound(int value) {
        if (soundManager.isReady()) {
            switch (item_selected) {
                case "Extras":
                    EXTRAS sound_extra = getExtraSound(value);
                    soundManager.play_extra(sound_extra);
                    break;
                case "Human":
                    HUMAN sound_human = getHumanSound(value);
                    soundManager.play_human(sound_human);
                    break;
                case "Vehicle":
                    VEHICLE sound_vehicle = getVehicleSound(value);
                    soundManager.play_vehicle(sound_vehicle);
                    break;
                case "Nature":
                    NATURE sound_nature = getNatureSound(value);
                    soundManager.play_nature(sound_nature);
                    break;
                default:
                    Log.i("Sound", "Unable to get the sound");
                    break;
            }
        }
    }

    private HUMAN getHumanSound(int value) {
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

    private NATURE getNatureSound(int value) {
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

    public EXTRAS getExtraSound(int value) {
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

    private VEHICLE getVehicleSound(int value) {
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
}


