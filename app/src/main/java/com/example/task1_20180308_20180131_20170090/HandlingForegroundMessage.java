package com.example.task1_20180308_20180131_20170090;

import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class HandlingForegroundMessage extends FirebaseMessagingService {
    private static final String CHANNEL_ID = "101";
    private final  String TAG="FirebaseMessagingServic";
    FirebaseDatabase rootNode;
    DatabaseReference reference;
@Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage)
    {
        Log.d(TAG, "onMessageReceived: "+remoteMessage.getNotification().getTitle());
        Log.d(TAG, "onMessageReceived: "+remoteMessage.getNotification().getBody());


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
        /*creating an instance of the notifications class*/
        String notificationTitle = title;
        String notificationMessage = message;
        Notifications notification= new Notifications(notificationTitle,notificationMessage);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);


        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
        /*sending the created instance upon sending*/
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Notofications");
        reference.setValue(notification);
    }



}
