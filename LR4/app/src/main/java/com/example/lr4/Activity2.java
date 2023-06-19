package com.example.lr4;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;

    private TextView phonebook;
    private TextView callbackStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        // Contacts
        phonebook = findViewById(R.id.phonebook);

        // Logging TextView
        callbackStatusTextView = findViewById(R.id.callback_status);

        // Request contacts permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE);
        } else {
            getContact();
        }

        // Open call screen activity (MainActivity)
        findViewById(R.id.button2).setOnClickListener(v ->
                startActivity(new Intent(Activity2.this, MainActivity.class)));

        // Log method call
        callbackStatusTextView.append("onCreate()\n");
    }

    private void getContact() {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);

        ArrayList<String> contactsList = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Get contact name
                @SuppressLint("Range") String name = cursor.getString(
                        cursor.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY));

                // Get contact phone
                @SuppressLint("Range") String mobile = cursor.getString(
                        cursor.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));

                // Add to list
                contactsList.add(name + " - " + mobile);
            } while (cursor.moveToNext());

            cursor.close();
        }

        // Convert list to string and add contacts to the TextView
        phonebook.setText(contactsList.toString());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Log method call
        callbackStatusTextView.append("onRequestPermissionsResult()\n");

        // Get list of contacts
        if (requestCode == REQUEST_CODE && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getContact();
        }
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