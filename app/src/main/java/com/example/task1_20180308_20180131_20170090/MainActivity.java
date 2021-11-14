package com.example.task1_20180308_20180131_20170090;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "101";
    private final  String TAG="FirebaseMessagingServic";
  //  DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //addtodb("nadaaaa","testt");
        FirebaseMessaging.getInstance().subscribeToTopic("topic");
        createNotificationChannel();
        getToken();


    }

   private void getToken()
    {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    Log.d(TAG, "onComplete: Failed to get the Token");
                }

                //Token
                String token = task.getResult();
                Log.d(TAG, "onComplete: " + token);
            }
        });

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "FirebaseMessagingServic";
            String description = "Receve Firebase notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


//    public void addtodb (String body, String title)
//    {
//
//        Map<String, Object> message = new HashMap<>();
//        message.put("title", title);
//        message.put("body", body);
//        dbref= FirebaseDatabase.getInstance().getReference().child("Notifications");
//        dbref.push().setValue(message);
//    }


    }

