package com.example.guesstheceleb;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.guesstheceleb.game.Game;
import com.example.guesstheceleb.game.Question;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class QuestionFragment extends Fragment {
    private StateListener listener;
    private Game currentGame;
    private int questionNumber;
    private Question currentQuestion;
    private ImageView imageView;
    private GridView possbileAnswers;
    private ArrayAdapter<String> adapter;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (StateListener) context;
    }

    public void setGame(Game game) {
        currentGame = game;
        questionNumber = 0;
        showNextQuestion();
    }

    private void showNextQuestion() {
        questionNumber++;
        if (questionNumber > currentGame.count()) {
            listener.onUpdate(State.GAME_OVER);
        } else {
            listener.onUpdate(State.CONTINUE_GAME);
            currentQuestion = currentGame.next();
            adapter.clear();

            String[] answers = currentQuestion.getPossibleNames();

            List<String> shuffleList = Arrays.asList(answers);
            Collections.shuffle(shuffleList);

            String[] shuffledAnswers = shuffleList.toArray(new String[0]);
            adapter.addAll(shuffledAnswers);
            imageView.setImageBitmap(currentQuestion.getCelebrityImage());
        }
    }

    public String getScore() {
        return currentGame.getScore();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_question, container, false);

        possbileAnswers = view.findViewById(R.id.options);
        imageView = view.findViewById(R.id.image);
        adapter = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1);
        possbileAnswers.setAdapter(adapter);
        possbileAnswers.setOnItemClickListener(((parent, view1, position, id) -> {
            TextView answer = (TextView) view1;
            currentGame.updateScore(currentQuestion.check(answer.getText().toString()));
            showNextQuestion();
        }));

        return view;
    }

    void setVisibility() {
        possbileAnswers.setVisibility(View.VISIBLE);
    }
}
