package com.example.lr4;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;

    private EditText phoneNumber;
    private TextView callbackStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Call number EditText
        phoneNumber = findViewById(R.id.editTextPhone);

        // Logging TextView
        callbackStatusTextView = findViewById(R.id.callback_status2);

        // Grant call permissions
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_CODE);
        }

        // Start call intent by pressing call button
        findViewById(R.id.floatingActionButton).setOnClickListener(v -> {
            Intent intent2 = new Intent(Intent.ACTION_CALL);
            intent2.setData(Uri.parse("tel:" + phoneNumber.getText().toString()));
            startActivity(intent2);
        });

        // Open phone book activity
        findViewById(R.id.button).setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), Activity2.class)));

        // Log method call
        callbackStatusTextView.append("onCreate()\n");
    }

    @Override
    protected void onStart() {
        super.onStart();
        callbackStatusTextView.append("onStart()\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        callbackStatusTextView.append("onResume()\n");
    }

    @Override
    protected void onPause() {
        super.onPause();
        callbackStatusTextView.append("onPause()\n");
    }

    @Override
    protected void onStop() {
        super.onStop();
        callbackStatusTextView.append("onStop()\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        callbackStatusTextView.append("onDestroy()\n");
    }
}
