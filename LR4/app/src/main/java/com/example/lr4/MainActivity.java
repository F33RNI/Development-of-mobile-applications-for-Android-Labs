package com.example.lr4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText phoneNo;
    private FloatingActionButton callbtn;
    private static final int PERMISSION_CODE = 100;
    private TextView callbackStatusTextView; // TextView для отображения информации о методах обратного вызова

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNo = findViewById(R.id.editTextPhone);
        callbtn = findViewById(R.id.floatingActionButton);
        callbackStatusTextView = findViewById(R.id.callback_status2); // Инициализация TextView для отображения информации о методах обратного вызова

        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, PERMISSION_CODE);
        }

        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneno = phoneNo.getText().toString();
                Intent intent2 = new Intent(Intent.ACTION_CALL);
                intent2.setData(Uri.parse("tel:" + phoneno));
                startActivity(intent2);
            }
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        updateCallbackStatus2("onCreate"); // Обновление информации о методе обратного вызова onCreate
    }

    public void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateCallbackStatus2("onStart"); // Обновление информации о методе обратного вызова onStart
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCallbackStatus2("onResume"); // Обновление информации о методе обратного вызова onResume
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateCallbackStatus2("onPause"); // Обновление информации о методе обратного вызова onPause
    }

    @Override
    protected void onStop() {
        super.onStop();
        updateCallbackStatus2("onStop"); // Обновление информации о методе обратного вызова onStop
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        updateCallbackStatus2("onDestroy"); // Обновление информации о методе обратного вызова onDestroy
    }

    private void updateCallbackStatus2(String callbackMethod) {
        String currentStatus = callbackStatusTextView.getText().toString();
        String updatedStatus = currentStatus + "\n" + callbackMethod;
        callbackStatusTextView.setText(updatedStatus);
    }
}
