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
 
package com.example.lab3_notifications;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {
    private static final int notificationId = 1;
    private static final String CHANNEL_ID = "my_channel_01";

    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);

        // Connect button
        findViewById(R.id.button).setOnClickListener(this::showDialog);

        // Create notification builder
        builder = new NotificationCompat.Builder(this, CHANNEL_ID);

        // Get notification manager
        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        // Create notification channel
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                "WINX Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(channel);
    }

    /**
     * Shows dialog with dialogCallback() as positive callback
     * @param v View
     */
    public void showDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setView(R.layout.dialog)
                .setPositiveButton("Yes", (dialog, which) -> dialogCallback())
                .setNegativeButton("No", null)
                .show();
    }


    /**
     * Shows notification and toast
     */
    private void dialogCallback() {
        // Sow toast
        Toast.makeText(this, "You've become Flora!", Toast.LENGTH_SHORT).show();

        // Set notification properties
        builder.setContentTitle("LR3");
        builder.setContentText("You've become Flora!");
        builder.setSmallIcon(R.drawable.winx);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        builder.setAutoCancel(true);

        // Build the notification
        Notification notification = builder.build();

        // Show notification
        notificationManager.notify(notificationId, notification);
    }
}