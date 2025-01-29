package com.example.app31;


import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);

        // Api 26+
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel notificationChannel = new NotificationChannel(
                    "C1",
                    "Chanel1",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification notification =new NotificationCompat.Builder(getApplicationContext(), "C1")
                .setContentTitle("Hello")
                .setContentText("This is a Sample Notification")
                .setSmallIcon(R.drawable.baseline_3p_24)
                .build();

        notificationManager.notify(1,notification);
    }
}
