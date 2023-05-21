package com.example.lab3_notifications;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);
    }

    public void showDialog(View v) {
        CustomDialogFragment dialog = new CustomDialogFragment(this);
        dialog.show(getSupportFragmentManager(), "custom");
    }
}