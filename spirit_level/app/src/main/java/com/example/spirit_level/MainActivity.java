package com.example.spirit_level;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor gravitySensor;
    private SpiritLevelView draw_bubble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        draw_bubble = findViewById(R.id.spirit_bubble);

        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor sensor : deviceSensors) {
            Log.i("MainActivity*****", sensor.getName());
            String sensor_name = sensor.getName();

            if (sensor_name.contains("GeoMag Rotation Vector")) {
                gravitySensor = sensor;
                Log.i("MainActivity***** ### 1", gravitySensor.getName());
            }
        }

        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ALL);
        Log.i("MainActivity***** ### 2", gravitySensor.getName());
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        final String[] axis = new String[]{"x", "y", "z"};
        final String format = "%s %.2f";

        StringBuilder stringBuildermessage = new StringBuilder();

        for (int i = 0; i < axis.length; i++) {
            String text = String.format(Locale.getDefault(), format, axis[i], event.values[i]);
            Log.i("MainActivity*Sensor", text);
            stringBuildermessage.append(text);
        }

        float x = event.values[0];
        //float y = event.values[1];  // This is never used.
        float z = event.values[2];
        draw_bubble.setBubble(x, z);
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
