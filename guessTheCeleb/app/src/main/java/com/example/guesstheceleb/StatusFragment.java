package com.example.guesstheceleb;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {

    private TextView messasge;
    private TextView score;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        StateListener listener = (StateListener) context;
    }

//    public StatusFragment() {
//        // Required empty public constructor
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        Log.i("StatusFragment *****", "So far so good");
        View view= inflater.inflate(R.layout.fragment_status, container, false);
        messasge = view.findViewById(R.id.message);
        score = view.findViewById(R.id.score);

        return view;
    }

    public String getMessasge(){return (String) messasge.getText();}
    public void setMessasge(String text){messasge.setText(text);}
    public void setScore(String text){score.setText(text);}

}
