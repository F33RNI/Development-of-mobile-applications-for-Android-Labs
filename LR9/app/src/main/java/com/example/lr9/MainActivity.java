/**
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org/>
 */

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    // System service for managing sensors
    private SensorManager sensorManager;

    // Search all sensors with ID up to this value
    private static final int SENSOR_ID_MAX = 1000;

    private final List<Sensor> sensorsList = new ArrayList<>();
    private final List<TextView> textViewsList = new ArrayList<>();


    //private Sensor mAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get system service
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Check if we have this service
        if (sensorManager != null) {
            // Get linear layout from activity
            LinearLayout linearLayout = findViewById(R.id.sensorsData);

            // Iterate all possible sensors
            for (int i = 0; i < SENSOR_ID_MAX; i++) {
                // Try to get them
                try {
                    Sensor sensor = sensorManager.getDefaultSensor(i);

                    // Check if sensor has name
                    if (sensor.getName().length() > 1) {
                        // Try to add it to the List
                        sensorsList.add(sensor);

                        // Create new TextView for sensor data
                        TextView textView = new TextView(this);

                        // Set sensor name
                        textView.setText(String.format("Sensor name: %s\nNo data yet...\n",
                                sensor.getName()));

                        // Add this TextView to the LinearLayout
                        linearLayout.addView(textView);

                        // Also, add it to the List of text views
                        textViewsList.add(textView);
                    }
                } catch (Exception ignored) { }
            }

            // Connect listeners
            registerListeners();
        }

        // We don't have service for sensors =(
        else {
            Toast.makeText(this, "Sensor service not available",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Registers (connects) listener for all sensors from List
     */
    private void registerListeners() {
        if (sensorManager != null) {
            for (Sensor sensor: sensorsList) {
                try {
                    sensorManager.registerListener(this, sensor,
                            SensorManager.SENSOR_DELAY_NORMAL);
                } catch (Exception ignored) { }
            }
        }
    }

    /**
     * Retrieves TextView from textViewsList based on Sensor object from sensorsList
     * @param sensor Sensor object (from sensorsList)
     * @return TextView (textViewsList)
     */
    private TextView getTextViewBySensorID(Sensor sensor) {
        // Try to find sensor
        for (int i = 0; i < sensorsList.size(); i++) {
            if (sensorsList.get(i).equals(sensor)) {
                return textViewsList.get(i);
            }
        }

        // No sensor found
        return null;
    }

    /**
     * Updates sensor's data into TextViews
     * @param sensorEvent SensorEvent object (from connected listener)
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Try to get matching TextView
        TextView textView = getTextViewBySensorID(sensorEvent.sensor);

        // Check if TextView exists
        if (textView != null)
            textView.setText(String.format("Sensor name: %s\nSensor data: %s\n",
                    sensorEvent.sensor.getName(), Arrays.toString(sensorEvent.values)));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register listeners back after activity resuming
        registerListeners();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Unregister listeners on activity pause
        sensorManager.unregisterListener(this);
    }
}
