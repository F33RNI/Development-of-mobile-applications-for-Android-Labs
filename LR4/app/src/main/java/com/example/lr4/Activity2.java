package com.example.lr4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    private Button button2;
    private TextView phonebook;
    private ArrayList<String> arrayList;
    private TextView callbackStatusTextView; // TextView для отображения информации о методах обратного вызова

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        phonebook = findViewById(R.id.phonebook);
        arrayList = new ArrayList<>();
        callbackStatusTextView = findViewById(R.id.callback_status); // Инициализация TextView для отображения информации о методах обратного вызова

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            getContact();
        }

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });

        updateCallbackStatus("onCreate"); // Обновление информации о методе обратного вызова onCreate
    }

    private void openActivity1() {
        Intent intent = new Intent(Activity2.this, MainActivity.class);
        startActivity(intent);
    }

    private void getContact() {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY));
                @SuppressLint("Range") String mobile = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                arrayList.add(name + " - " + mobile);
            } while (cursor.moveToNext());

            cursor.close();
        }

        phonebook.setText(arrayList.toString());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getContact();
        }
        updateCallbackStatus("onRequestPermissionsResult"); // Обновление информации о методе обратного вызова onRequestPermissionsResult
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateCallbackStatus("onStart"); // Обновление информации о методе обратного вызова onStart
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCallbackStatus("onResume"); // Обновление информации о методе обратного вызова onResume
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateCallbackStatus("onPause"); // Обновление информации о методе обратного вызова onPause
    }

    @Override
    protected void onStop() {
        super.onStop();
        updateCallbackStatus("onStop"); // Обновление информации о методе обратного вызова onStop
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        updateCallbackStatus("onDestroy"); // Обновление информации о методе обратного вызова onDestroy
    }

    private void updateCallbackStatus(String callbackMethod) {
        String currentStatus = callbackStatusTextView.getText().toString();
        String updatedStatus = currentStatus + "\n" + callbackMethod;
        callbackStatusTextView.setText(updatedStatus);
    }
}