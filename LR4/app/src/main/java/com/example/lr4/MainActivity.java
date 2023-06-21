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
