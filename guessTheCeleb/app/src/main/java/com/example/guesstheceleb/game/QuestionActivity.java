package com.example.guesstheceleb.game;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.guesstheceleb.QuestionFragment;
import com.example.guesstheceleb.R;
import com.example.guesstheceleb.State;
import com.example.guesstheceleb.StateListener;
import com.example.guesstheceleb.StatusFragment;

public class QuestionActivity extends AppCompatActivity implements StateListener {

    private QuestionFragment questionFragment;
    private StatusFragment statusFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        FragmentManager fragmentManager = getSupportFragmentManager();
        questionFragment = (QuestionFragment) fragmentManager.findFragmentById(R.id.question);
        statusFragment = (StatusFragment) fragmentManager.findFragmentById(R.id.status);

        CelebrityManager celebrityManager = new CelebrityManager(getAssets(), "celebs");

        GameBuilder gameBuilder = new GameBuilder(celebrityManager);

        Intent intent = getIntent();
        Difficulty level = (Difficulty) intent.getSerializableExtra("level");

        assert level != null;
        Game game = gameBuilder.create(level);
        questionFragment.setGame(game);
    }


    public void onUpdate(State state) {
        switch (state) {
            case CONTINUE_GAME:
                statusFragment.setScore(questionFragment.getScore());
                break;
            case GAME_OVER:
                if (!statusFragment.getMessasge().equals("Game Over!")) {
                    statusFragment.setScore(questionFragment.getScore());
                    statusFragment.setMessasge("Game Over!");
                }
                break;
        }
    }
}
