package com.example.utility_app;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_data() {
        boolean result;

        // Positive test cases
        result = test_positive_cases("Imperial", 50, 1,
                0, 400, 11, 12,
                10);
        assertTrue(result);

        result = test_positive_cases("Metrics", 25, 30,
                1, 200, 305, 1,
                15);
        assertTrue(result);

        // Negative test cases
        result = test_negative_scenario("Imperial", 400, 12,
                12);
        assertFalse(result);

        result = test_negative_scenario("Imperial", 49, 0,
                12);
        assertFalse(result);

        result = test_negative_scenario("Metrics", 20, 20,
                0);
        assertFalse(result);

        result = test_negative_scenario("Metrics", 210, 310,
                0);
        assertFalse(result);
    }

    private boolean test_positive_cases(String unit_selected, int initial_weight_value,
                                        int initial_height_value, int initial_height_inch_value,
                                        int max_weight_value, int max_height_value,
                                        int max_height_inch_value, int increment_weight_value) {

        // Valid test cases
        bmi bmi = new bmi();
        String gender_selected;
        boolean valid_data = false;

        if (unit_selected.equals("Imperial")) {
            for (int weight_value = initial_weight_value; weight_value < max_weight_value;
                 weight_value += increment_weight_value) {
                for (int height_value = initial_height_value; height_value < max_height_value;
                     height_value++) {
                    for (int height_inch_value = initial_height_inch_value;
                         height_inch_value < max_height_inch_value; height_inch_value++) {
                        for (int gender_id = 0; gender_id < 2; gender_id++) {
                            if (gender_id == 0) {
                                gender_selected = "Male";
                            } else gender_selected = "Female";

                            valid_data = bmi.validate_data(unit_selected,
                                    weight_value, height_value, height_inch_value, gender_selected);
                            String result = ("Unit: " + unit_selected + ", Weight: " + weight_value
                                    + " lbs, Height: " + height_value + " ft, " + height_inch_value
                                    + " inch, Gender: " + gender_selected + ": " + valid_data);
                            System.out.println(result);
                        }
                    }
                }
            }
        } else {
            for (int weight_value = initial_weight_value; weight_value < max_weight_value;
                 weight_value += increment_weight_value) {
                for (int height_value = initial_height_value; height_value < max_height_value;
                     height_value++) {
                    for (int gender_id = 0; gender_id < 2; gender_id++) {
                        if (gender_id == 0) {
                            gender_selected = "Male";
                        } else gender_selected = "Female";

                        valid_data = bmi.validate_data(unit_selected,
                                weight_value, height_value, 0, gender_selected);
                        String result = ("Unit: " + unit_selected + ", Weight: " + weight_value +
                                " Kg, Height: " + height_value + " cm, Gender: " +
                                gender_selected + ": " + valid_data);
                        System.out.println(result);
                    }
                }
            }
        }
        return valid_data;
    }

    private boolean test_negative_scenario(String unit_selected, int weight_value, int height_value,
                                           int height_inch_value) {
        // Invalid test cases
        bmi bmi = new bmi();
        String gender_selected = "Female";
        String msg;

        boolean valid_data = bmi.validate_data(unit_selected,
                weight_value, height_value, height_inch_value, gender_selected);

        if (unit_selected.equals("Imperial")) {
            msg = ("Weight: " + weight_value + " lbs, Height: " + height_value +
                    " ft, " + height_inch_value + " inch, Gender: " + gender_selected +
                    ": " + valid_data);
        } else {
            msg = ("Weight: " + weight_value + " kg, Height: " + height_value +
                    " cms, Gender: " + gender_selected + ": " + valid_data);
        }
        System.out.println(msg);
        return valid_data;

    }
}