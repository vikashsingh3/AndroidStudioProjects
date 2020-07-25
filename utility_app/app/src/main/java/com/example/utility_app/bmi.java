package com.example.utility_app;

import java.text.DecimalFormat;

class bmi {

    private static DecimalFormat df = new DecimalFormat("0.00");

    boolean validate_data(String unitSelected, int weight, int height, int inch, String gender) {
        if (unitSelected.equals("Imperial")) {
            // lbs (between 50 and 400) / ft (between 1 and 10) / inch (up to 11)
            return !(weight < 50 | weight > 400 | height < 1 | height > 10 | inch > 11);
        } else {
            return !(weight > 200 | weight < 25 | height < 30 | height > 305);
        }
    }

    String invalid_reason(String unitSelected, int weight, int height, int inch, String gender) {
        if (unitSelected.equals("Imperial")) {
            if (weight > 400 | weight < 50) {
                return "Invalid Weight. Enter weight between 50 and 400";
            } else if (height < 1 | height > 10) {
                return "Invalid Height. Enter height between 1 and 10";
            } else if (inch > 11) {
                return "Invalid Height. Inch should be less than 12";
            } else
                return "Unknown Error";
        } else {
            if (weight > 200 | weight < 25) {
                return "Invalid Weight. Enter weight between 25 and 200";
            } else if (height < 30 | height > 305) {
                return "Invalid Height. Enter height between 30 and 305";
            } else
                return "Unknown Error";
        }
    }

    double calculate_bmi(String unitSelected, int weight, int height, int inch, String gender) {
        String bmi;
        if (unitSelected.equals("Imperial")) {
            bmi = df.format(703 * weight / Math.pow((height * 12 + inch), 2));
        } else {
            bmi = df.format(weight / Math.pow(height / 100.0, 2));
        }
        return Double.parseDouble(bmi);
    }
}
