package com.example.utility_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String unit_selected = get_Unit_Selected();
        Boolean back_ground_color = get_background_Selected();
        set_display(unit_selected, back_ground_color);
    }

    public void btnBMI_Clicked(View view) {
        TextView weight = findViewById(R.id.txt_Weight);
        TextView height = findViewById(R.id.txt_Height);
        TextView height_inch = findViewById(R.id.txt_Height_inch);
        RadioGroup radioGroup = findViewById(R.id.genderRadioGroup);
        TextView result = findViewById(R.id.txt_Result);

        String gender_selected = null;
        CharSequence weight_value = weight.getText();
        CharSequence height_value = height.getText();
        CharSequence height_inch_value = height_inch.getText();
        String msg = "";
        int background_color = 0;
        int text_color = Color.BLACK;

        String unit_selected = get_Unit_Selected();
        // Validate entries
        if (radioGroup.getCheckedRadioButtonId() == -1) msg += "Please select your gender\n";
        else {
            int id = radioGroup.getCheckedRadioButtonId();
            View radioButton = radioGroup.findViewById(id);
            int radioId = radioGroup.indexOfChild(radioButton);
            RadioButton rbtn = (RadioButton) radioGroup.getChildAt(radioId);
            gender_selected = (String) rbtn.getText();
        }
        if (weight_value.length() < 1) msg += "Please enter your weight\n";
        if (height_value.length() < 1) msg += "Please enter your height\n";
        if (height_inch_value.length() < 1) height_inch_value = "0";

        if (msg.equals("")) {
            bmi bmi = new bmi();
            boolean validate_data = bmi.validate_data(unit_selected,
                    Integer.parseInt(weight_value.toString()),
                    Integer.parseInt(height_value.toString()),
                    Integer.parseInt(height_inch_value.toString()),
                    gender_selected);
            if (validate_data) {
                // Calculate BMI
                double bmi_value = bmi.calculate_bmi(unit_selected,
                        Integer.parseInt(weight_value.toString()),
                        Integer.parseInt(height_value.toString()),
                        Integer.parseInt(height_inch_value.toString()),
                        gender_selected);

                String bmi_status;
                if (bmi_value < 18.5) {
                    bmi_status = "You are underweight";
                    background_color = Color.BLUE;
                    text_color = Color.WHITE;
                } else if (bmi_value < 25.0) {
                    bmi_status = "You are healthy";
                    background_color = Color.GREEN;
                    text_color = Color.BLACK;
                } else if (bmi_value < 30.0) {
                    bmi_status = "You are over weight";
                    background_color = Color.rgb(255, 165, 0); //AMBER
                    text_color = Color.BLACK;
                } else {
                    bmi_status = "You are obese";
                    background_color = Color.RED;
                    text_color = Color.WHITE;
                }

                msg = msg + "BMI = " + bmi_value + "\n" + bmi_status;
            } else {
                msg = bmi.invalid_reason(unit_selected,
                        Integer.parseInt(weight_value.toString()),
                        Integer.parseInt(height_value.toString()),
                        Integer.parseInt(height_inch_value.toString()),
                        gender_selected);
            }
        }
        result.setText(msg);
        result.setBackgroundColor(background_color);
        result.setTextColor(text_color);

    }

    public void btnSetting_Clicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void btnAbout_Clicked(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    // Get the unit metrics selected
    public String get_Unit_Selected() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String sSetting = pref.getString("unit", "Metrics");

        if (sSetting.equals("Imperial")) {
            return "Imperial";
        } else {
            return "Metrics";
        }
    }

    // Set the display based on unit setting
    public void set_display(String unitSet, Boolean background_color) {
        TextView weight_label = findViewById(R.id.lbl_weight_unit);
        TextView height_label = findViewById(R.id.lbl_height_unit);
        TextView height_inch_label = findViewById(R.id.lbl_height_unit_inch);
        EditText height_inch_text_value = findViewById(R.id.txt_Height_inch);
        LinearLayout layout = findViewById(R.id.description_linear_Layout);

        if (unitSet.equals("Imperial")) {
            weight_label.setText(R.string.Imperial_lbs);
            height_label.setText(R.string.Imperial_feet);
            height_inch_label.setText(R.string.Imperial_inch);
            height_inch_label.setVisibility(View.VISIBLE);
            height_inch_text_value.setVisibility(View.VISIBLE);
        } else {
            weight_label.setText(R.string.metrics_kg);
            height_label.setText(R.string.metrics_cm);
            height_inch_label.setVisibility(View.INVISIBLE);
            height_inch_text_value.setVisibility(View.INVISIBLE);
        }
        if (background_color) {
            layout.setBackgroundColor(Color.DKGRAY);
            set_font_colors(Color.WHITE);
        } else {
            layout.setBackgroundColor(Color.WHITE);
            set_font_colors(Color.BLACK);
        }
    }

    // Get the background selected
    public Boolean get_background_Selected() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        return pref.getBoolean("background_setting", false);
    }

    // set font colors
    public void set_font_colors(int fontColors) {
        TextView weight_label = findViewById(R.id.lbl_weight_unit);
        TextView height_label = findViewById(R.id.lbl_height_unit);
        TextView weight = findViewById(R.id.txt_Weight);
        TextView height = findViewById(R.id.txt_Height);
        TextView height_inch = findViewById(R.id.txt_Height_inch);
        TextView txtGender = findViewById(R.id.textViewGender);
        RadioButton radioButtonMale = findViewById(R.id.radioButtonMale);
        RadioButton radioButtonFemale = findViewById(R.id.radioButtonFemale);
        TextView lbl_Weight = findViewById(R.id.lbl_Weight);
        TextView lbl_Height = findViewById(R.id.lbl_Height);
        TextView lbl_height_unit_inch = findViewById(R.id.lbl_height_unit_inch);
        TextView result = findViewById(R.id.txt_Result);

        weight.setTextColor(fontColors);
        weight_label.setTextColor(fontColors);
        height.setTextColor(fontColors);
        height_inch.setTextColor(fontColors);
        height_label.setTextColor(fontColors);
        txtGender.setTextColor(fontColors);
        radioButtonMale.setTextColor(fontColors);
        radioButtonFemale.setTextColor(fontColors);
        lbl_Weight.setTextColor(fontColors);
        lbl_Height.setTextColor(fontColors);
        lbl_height_unit_inch.setTextColor(fontColors);
        result.setTextColor(fontColors);
    }
}
