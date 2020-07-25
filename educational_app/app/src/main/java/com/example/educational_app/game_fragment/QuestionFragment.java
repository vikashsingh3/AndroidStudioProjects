package com.example.educational_app.game_fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.educational_app.R;
import com.example.educational_app.State;
import com.example.educational_app.StateListener;
import com.example.educational_app.game_helper.SqliteHelper;
import com.example.educational_app.game_manager.Question;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {
    private TextView question;
    private Button option_1;
    private Button option_2;
    private Button option_3;
    private Button option_4;
    private StateListener listener;
    private StateListener listenerImage;
    private int question_number;
    private int question_total_count;
    private int score;
    private String imageName;
    private List<Question> questionList;
    private String correctAnswer;

    private static final int EASY = 5;
    private static final int MEDIUM = 8;
    private static final int HARD = 12;
    private static final int EXPERT = 16;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listenerImage = (StateListener) context;
        listener = (StateListener) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get the game level set in preference
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getActivity()));
        String game_level = pref.getString("game_level", "Easy");

        int number_of_questions;
        switch (game_level.toUpperCase()) {
            case "EXPERT":
                number_of_questions = EXPERT;
                break;
            case "HARD":
                number_of_questions = HARD;
                break;
            case "MEDIUM":
                number_of_questions = MEDIUM;
                break;
            default:
                number_of_questions = EASY;
                break;
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        question = view.findViewById(R.id.label_question);
        option_1 = view.findViewById(R.id.option_1);
        option_2 = view.findViewById(R.id.option_2);
        option_3 = view.findViewById(R.id.option_3);
        option_4 = view.findViewById(R.id.option_4);

        Question questionNo = new Question();
        setOption_1(questionNo.getOption_1());
        setOption_2(questionNo.getOption_2());
        setOption_3(questionNo.getOption_3());
        setOption_4(questionNo.getOption_4());
        setQuestion(questionNo.getQuestion());

        // Set the options
        option_1.setText(questionNo.getOption_1());
        option_2.setText(questionNo.getOption_2());
        option_3.setText(questionNo.getOption_3());
        option_3.setText(questionNo.getOption_4());
        question.setText(questionNo.getQuestion());

        SqliteHelper sqliteHelper = new SqliteHelper(getActivity());
        questionList = sqliteHelper.getAllQuestions(number_of_questions);
        question_total_count = questionList.size();
        Collections.shuffle(questionList);

        if (savedInstanceState != null) {
            //Set the values from saved instance
            question_number = savedInstanceState.getInt("question_number");
            score = savedInstanceState.getInt("score");
            setOption_1(savedInstanceState.getString("option_1_saved"));
            setOption_2(savedInstanceState.getString("option_2_saved"));
            setOption_3(savedInstanceState.getString("option_3_saved"));
            setOption_4(savedInstanceState.getString("option_4_saved"));
            setQuestion(savedInstanceState.getString("question_saved"));
            correctAnswer = savedInstanceState.getString("correctAnswer_saved");
        }

        //Check button clicked
        option_1.setOnClickListener((v) -> {
            String guessed_answer = (String) option_1.getText();
            checkAnswer(guessed_answer);
        });
        option_2.setOnClickListener((v) -> {
            String guessed_answer = (String) option_2.getText();
            checkAnswer(guessed_answer);
        });
        option_3.setOnClickListener((v) -> {
            String guessed_answer = (String) option_3.getText();
            checkAnswer(guessed_answer);
        });
        option_4.setOnClickListener((v) -> {
            String guessed_answer = (String) option_4.getText();
            checkAnswer(guessed_answer);
        });

        return view;
    }

    public void displayNextQuestion() {
        if (question_number < question_total_count) {
            Question currentQuestion = questionList.get(question_number);
            setQuestion(currentQuestion.getQuestion());
            setOption_1(currentQuestion.getOption_1());
            setOption_2(currentQuestion.getOption_2());
            setOption_3(currentQuestion.getOption_3());
            setOption_4(currentQuestion.getOption_4());
            setImageName(currentQuestion.getImage_name());
            correctAnswer = currentQuestion.getAnswer();
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer(String answer_selected) {
        boolean answer_valid = answer_selected.equals(correctAnswer);
        question_number++;
        if (answer_valid) {
            score++;
        }
        listener.onUpdate(State.CONTINUE_GAME);
        displayNextQuestion();
    }

    private void setImageName(String text) {
        imageName = text;
        listenerImage.onUpdate(State.LOAD_IMAGE);
    }

    public String getImageName() {
        return imageName;
    }

    private void finishQuiz() {
        listener.onUpdate(State.GAME_OVER);
    }

    private void setQuestion(String text) {
        question.setText(text);
    }

    private void setOption_1(String text) {
        option_1.setText(text);
    }

    private void setOption_2(String text) {
        option_2.setText(text);
    }

    private void setOption_3(String text) {
        option_3.setText(text);
    }

    private void setOption_4(String text) {
        option_4.setText(text);
    }

    public String getScore() {
        return "Score: " + score;
    }

    public String getQuestionNumbers() {
        // to be displayed in status fragment
        String status_question;
        if (question_number == question_total_count) {
            status_question = "Question: " + (question_number) + "/" + question_total_count;
        } else {
            status_question = "Question: " + (question_number + 1) + "/" + question_total_count;
        }
        return status_question;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("question_number", question_number);
        savedInstanceState.putInt("score", score);
        savedInstanceState.putString("question_saved", question.getText().toString());
        savedInstanceState.putString("option_1_saved", option_1.getText().toString());
        savedInstanceState.putString("option_2_saved", option_2.getText().toString());
        savedInstanceState.putString("option_3_saved", option_3.getText().toString());
        savedInstanceState.putString("option_4_saved", option_4.getText().toString());
        savedInstanceState.putString("imageName_saved", imageName);
        savedInstanceState.putString("correctAnswer_saved", correctAnswer);
    }
}
