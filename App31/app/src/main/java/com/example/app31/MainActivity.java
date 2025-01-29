package com.example.app31;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)!= PackageManager.PERMISSION_DENIED){
                    requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS},1);
                    return;
                }

                showNotification();

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, int deviceId) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId);
        if(requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            showNotification();
        }
    }

    public void showNotification(){
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

        // notification
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                MainActivity.this,
                100,
                intent,
                PendingIntent.FLAG_IMMUTABLE
        );

//        Notification notification =new Notification.Builder(MainActivity.this, "C1")
//                .setContentTitle("Hello")
//                .setContentText("This is a Sample Notification")
//                .setPriority(Notification.PRIORITY_DEFAULT)
//                .setSmallIcon(R.drawable.baseline_3p_24)
//                .setActions(
//                        new Notification.Action(
//                                R.drawable.baseline_3p_24,
//                                "View",
//                                pendingIntent
//                        )
//                )
//                .build();

        NotificationCompat.Action action = new NotificationCompat.Action.Builder(
                R.drawable.baseline_3p_24,
                "View",
                pendingIntent
        ).build();

        Notification notification =new NotificationCompat.Builder(MainActivity.this, "C1")
                .setContentTitle("Hello")
                .setContentText("This is a Sample Notification")
                .setSmallIcon(R.drawable.baseline_3p_24)
                .addAction(action)
                .build();

        notificationManager.notify(1,notification);
    }
}