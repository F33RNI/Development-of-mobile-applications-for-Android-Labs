package com.example.lab3_notifications;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;
import androidx.annotation.NonNull;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Build;

public class CustomDialogFragment extends DialogFragment {
    int notificationId = 1;String CHANNEL_ID = "my_channel_01";
    @NonNull

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Вы уверены?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setView(R.layout.dialog)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Теперь вы фея!", Toast.LENGTH_SHORT).show();
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID).setSmallIcon(R.drawable.winx).setContentTitle("Уведомлениевстрокесостояния").setContentText("Текстсообщения").setPriority(NotificationCompat.PRIORITY_DEFAULT);
                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());notificationManager.notify(notificationId, builder.build());
                    }


                })
                .setNegativeButton("Ненадо", null)
                .create();
    }
}