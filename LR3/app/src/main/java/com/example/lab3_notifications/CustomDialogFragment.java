package com.example.lab3_notifications;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;
import androidx.annotation.NonNull;

import android.content.Context;

public class CustomDialogFragment extends DialogFragment {
    private static final int notificationId = 1;
    private static final String CHANNEL_ID = "my_channel_01";

    private final Context context;
    private final NotificationCompat.Builder builder;
    private final NotificationManager notificationManager;

    public CustomDialogFragment(Context context) {
        this.context = context;
        builder = new NotificationCompat.Builder(context, CHANNEL_ID);

        // Get notification manager
        notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Create notification channel
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                "WINX Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(channel);
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Вы уверены?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setView(R.layout.dialog)
                .setPositiveButton("Да", (dialog, which) -> dialogCallback())
                .setNegativeButton("Нинадо", null)
                .create();
    }

    /**
     * This function will be called on Да press
     */
    private void dialogCallback() {
        Toast.makeText(context, "Теперь вы фея!", Toast.LENGTH_SHORT).show();

        // Set notification properties
        builder.setContentTitle("УРА");
        builder.setContentText("Теперь вы фея!");
        builder.setSmallIcon(R.drawable.winx);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        builder.setAutoCancel(true);

        // Build the notification
        Notification notification = builder.build();

        // Show notification
        notificationManager.notify(notificationId, notification);
    }
}