package com.example.reflexchecker;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setupDescription(R.id.task1, R.array.task1_descriptions);
        setupDescription(R.id.task2, R.array.task2_descriptions);

        for (int i = 1; i <= 5; i++) {
            addImage();
            addCheckboxes(i);
        }
    }

    private void setupDescription(int taskID, int arrayID) {
        TextView task = findViewById(taskID);
        String[] description = getResources().getStringArray(arrayID);

        int i = random.nextInt(description.length);
        task.setText(description[i]);
    }

    private static final int[] drawables = {
            R.drawable.baseline_fastfood_black_48,
            R.drawable.baseline_local_drink_black_48,
            R.drawable.baseline_insert_emoticon_black_48,
            R.drawable.baseline_local_grocery_store_black_48,
            R.drawable.baseline_bluetooth_searching_black_48,
            R.drawable.baseline_dialpad_black_48,
            R.drawable.baseline_free_breakfast_black_48,
            R.drawable.baseline_headset_black_48,
            R.drawable.baseline_local_airport_black_48,
            R.drawable.baseline_local_pharmacy_black_48,
            R.drawable.baseline_restaurant_black_48,
            R.drawable.baseline_store_black_48
    };

    private void addImage() {
        ViewGroup gameRows = findViewById(R.id.game_rows);
        getLayoutInflater().inflate(R.layout.image, gameRows);

        View lastChild = gameRows.getChildAt(gameRows.getChildCount() - 1);
        ImageView image = lastChild.findViewById(R.id.image);

        int index = random.nextInt(drawables.length);
        image.setImageDrawable(getResources().getDrawable(drawables[index]));
    }


    private void addCheckboxes(int i) {
        int arrayID;
        if (i % 2 == 0) {
            arrayID = R.array.fruits;
        } else {
            arrayID = R.array.drinks;
        }
        ViewGroup gameRows = findViewById(R.id.game_rows);
        getLayoutInflater().inflate(R.layout.checkboxes, gameRows);

        View lastChild = gameRows.getChildAt(gameRows.getChildCount() - 1);
        CheckBox checkBox_1 = lastChild.findViewById(R.id.checkbox_1);
        CheckBox checkBox_2 = lastChild.findViewById(R.id.checkbox_2);
        CheckBox checkBox_3 = lastChild.findViewById(R.id.checkbox_3);

        String[] checkbox_text_array = getResources().getStringArray(arrayID);
        checkBox_1.setText(checkbox_text_array[0]);
        checkBox_2.setText(checkbox_text_array[1]);
        checkBox_3.setText(checkbox_text_array[2]);
    }

    // Go to main screen
    public void btn_done(View view) {
        finish();
    }
}
