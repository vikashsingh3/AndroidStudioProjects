package com.example.educational_app.game_activity;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.educational_app.R;
import com.example.educational_app.State;
import com.example.educational_app.StateListener;
import com.example.educational_app.game_fragment.ImageFragment;
import com.example.educational_app.game_fragment.QuestionFragment;
import com.example.educational_app.game_fragment.StatusFragment;

import java.io.IOException;
import java.io.InputStream;

public class GameActivity extends AppCompatActivity implements StateListener {

    private AssetManager assetManager;
    private StatusFragment statusFragment;
    private QuestionFragment questionFragment;
    private ImageFragment imageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        FragmentManager fragmentManager = getSupportFragmentManager();
        statusFragment = (StatusFragment) fragmentManager.findFragmentById(R.id.fragment_status);
        questionFragment = (QuestionFragment) fragmentManager.findFragmentById(R.id.fragment_question);
        imageFragment = (ImageFragment) fragmentManager.findFragmentById(R.id.fragment_image);

        assetManager = this.getAssets();
        questionFragment.displayNextQuestion();
    }

    private Bitmap getImageBitmap(AssetManager assetManager, String imageName) {
        try {
            String path = "places/" + imageName;
            InputStream stream = assetManager.open(path);
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            stream.close();
            return bitmap;
        } catch (IOException e) {
            Log.i("GameActivity", "Unable to open image");
            return null;
        }
    }

    public void onUpdate(State state) {
        switch (state) {
            case START_GAME:
            case CONTINUE_GAME:
                statusFragment.setScore(questionFragment.getScore());
                statusFragment.setQuestion_number(questionFragment.getQuestionNumbers());
                break;
            case LOAD_IMAGE:
                String image = questionFragment.getImageName();
                setImageFragment(image);
                break;
            case GAME_OVER:
                statusFragment.completeGame();
                break;
        }
    }

    public void setImageFragment(String imageName) {
        Bitmap bitmap_image = getImageBitmap(assetManager, imageName);
        imageFragment.setImageView(bitmap_image);
    }
}
