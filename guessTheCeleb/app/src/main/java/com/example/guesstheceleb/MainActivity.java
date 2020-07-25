package com.example.guesstheceleb;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.guesstheceleb.game.CelebrityManager;
import com.example.guesstheceleb.game.Difficulty;
import com.example.guesstheceleb.game.Game;
import com.example.guesstheceleb.game.GameBuilder;
import com.example.guesstheceleb.game.QuestionActivity;

public class MainActivity extends AppCompatActivity implements StateListener {
    private GameFragment gameFragment;
    private StatusFragment statusFragment;
    private QuestionFragment questionFragment;
    private boolean isLargeScreen;
    private GameBuilder gameBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        try {
            CelebrityManager imagemanager = new CelebrityManager(getAssets(), "celebs");
            gameBuilder = new GameBuilder(imagemanager);
        } catch (NullPointerException e) {
            System.out.println("Internal error");
        }

        gameFragment = (GameFragment) fragmentManager.findFragmentById(R.id.fragment_game);
        statusFragment = (StatusFragment) fragmentManager.findFragmentById(R.id.fragment_status);
        questionFragment = (QuestionFragment) fragmentManager.findFragmentById(R.id.fragment_question);
        isLargeScreen = statusFragment != null;
    }

    @Override
    public void onUpdate(State state) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        gameFragment = (GameFragment) fragmentManager.findFragmentById(R.id.fragment_game);
        assert gameFragment != null;
        Difficulty level = gameFragment.getLevel();

        if (isLargeScreen) {
            switch (state) {
                case START_GAME:
                    Game game = gameBuilder.create(level);
                    questionFragment.setGame(game);
                    questionFragment.setVisibility();
                    break;
                case CONTINUE_GAME:
                    statusFragment.setScore(questionFragment.getScore());
                    break;
                case GAME_OVER:
                    statusFragment.setScore(questionFragment.getScore());
                    statusFragment.setMessasge("Game Over!!");
                    break;
            }
        } else {
            if (state == State.START_GAME) {
                Intent intent = new Intent(this, QuestionActivity.class);
                intent.putExtra("level", level);
                startActivity(intent);
            } else if (state == State.GAME_OVER) {
                finish();
            }
        }
    }
}
