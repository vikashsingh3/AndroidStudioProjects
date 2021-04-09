package com.project.onlinequiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btn_ticket(View view) {
        Intent intent = new Intent(this, TicketActivity.class);
        startActivity(intent);
    }

}