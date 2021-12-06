package com.example.task1_20180308_20180131_20170090;

import android.app.PendingIntent;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

public class HandlingForegroundMessage extends FirebaseMessagingService {
    private static final String CHANNEL_ID = "101";
    private final  String TAG="FirebaseMessagingServic";
    DatabaseReference dbref;
@Override

    public void onMessageReceived(@NonNull RemoteMessage remoteMessage)
    {
        addtodb(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }

    public void addtodb (String title, String body)
    {

        Map<String, Object> message = new HashMap<>();
        message.put("title", title);
        message.put("body", body);
        FirebaseDatabase.getInstance().getReference("Notifications").child(message.toString()).setValue(message);
    }
    private void showNotification(String title,String message){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
     //   addtodb(message,title);
    }



}
