package com.example.stopwatch_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity extends Activity {

    private int seconds = 0;
    private boolean running;
    private int milliSecDelay=1000;
    private TextView timedelay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            milliSecDelay = savedInstanceState.getInt("milliSecDelay");
        }
        SeekBar seekbarsetting = findViewById(R.id.speedtime);
        timedelay = findViewById(R.id.textspeedtime);

        seekbarsetting.setBackgroundColor(Color.rgb(255,255,0));
        System.out.println("2nd attempt: "+ milliSecDelay);
        seekbarsetting.setProgress(milliSecDelay);
        timedelay.setText("" + milliSecDelay);

        seekbarsetting.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timedelay.setText("" + progress);
                milliSecDelay = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        runTimer();
    }

    public void buttonClickedStart(View view) {
        running = true;
    }

    public void buttonClickedStop(View view) {
        running = false;
    }


    public void buttonClickedReset(View view) {
        running = false;
        seconds = 0;
    }

    //Sets the number of seconds on the timer.
    private void runTimer(){
        final TextView textViewwatch = findViewById(R.id.textViewwatch);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                textViewwatch.setText(time);
                if (running){
                    seconds++;
                }
                handler.postDelayed(this, Integer.parseInt(timedelay.getText().toString()));
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putInt("milliSecDelay", milliSecDelay);
    }

}
