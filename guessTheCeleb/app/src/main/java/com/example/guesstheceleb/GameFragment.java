package com.example.guesstheceleb;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.guesstheceleb.game.Difficulty;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    private Difficulty level;
    private TextView level_selected;
    private StateListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (StateListener) context;
    }


    Difficulty getLevel() {
        return level;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_game, container, false);
        level_selected = view.findViewById(R.id.textView_level);
        final Spinner spinner = view.findViewById(R.id.spinner_level);

        view.findViewById(R.id.button_play).setOnClickListener((v) -> {
            String selection = spinner.getSelectedItem().toString();
            level = Difficulty.valueOf(selection.toUpperCase());
            level_selected.setText(level.toString());
            listener.onUpdate(State.START_GAME);
        });

        return view;
    }
}
