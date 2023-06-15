package com.example.lr9;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView txt_XYZ, txt_Lt;
    private SensorManager sensorManager;

    private final List<Sensor> sensorsList = new ArrayList<>();
    private final List<TextView> textViewsList = new ArrayList<>();


    //private Sensor mAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        LinearLayout linearLayout = findViewById(R.id.sensorsData);

        if (sensorManager != null) {
            for (int i = 0; i < 1000; i++) {
                try {
                    Sensor sensor = sensorManager.getDefaultSensor(i);
                    if (sensor.getName().length() > 1) {
                        sensorsList.add(sensor);

                        TextView textView = new TextView(this);

                        linearLayout.addView(textView);

                        textViewsList.add(textView);
                    }
                } catch (Exception ignored) { }
            }

            registerListeners();


            //Sensor sensor =
            /*mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            if (mAccelerometer != null) {
                mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            } else {
                Toast.makeText(this, "No accelerometer sensor detected", Toast.LENGTH_SHORT).show();
            }

            Sensor lightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

            if (lightSensor != null) {
                mSensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
            } else {
                Toast.makeText(this, "No light sensor detected", Toast.LENGTH_SHORT).show();
            }*/
        } else {
            Toast.makeText(this, "Sensor service not available", Toast.LENGTH_SHORT).show();
        }
    }

    private void registerListeners() {
        if (sensorManager != null) {
            for (Sensor sensor: sensorsList) {
                try {
                    sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
                } catch (Exception ignored) { }
            }
        }
    }

    private TextView getTextViewBySensorID(Sensor sensor) {
        for (int i = 0; i < sensorsList.size(); i++) {
            if (sensorsList.get(i).equals(sensor)) {
                return textViewsList.get(i);
            }

        }
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        TextView textView = getTextViewBySensorID(sensorEvent.sensor);
        if (textView != null) {
            textView.setText("Sensor: " + sensorEvent.sensor.getName() + " values: " + Arrays.toString(sensorEvent.values));
        }


        //System.out.println();


        /*if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            txt_XYZ.setText("X: " + sensorEvent.values[0] + ", Y: " + sensorEvent.values[1]
                    + ", Z: " + sensorEvent.values[2]);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            txt_Lt.setText("Value: " + sensorEvent.values[0]);
        }*/
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Не нужно делать ничего в этом методе
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerListeners();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
