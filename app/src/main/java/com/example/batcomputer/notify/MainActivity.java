package com.example.batcomputer.notify;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private NotificationManager mNotificationManager;
    private int notificationId =100;
    private int numMessages = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button startBtn = (Button) findViewById(R.id.start);
        startBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                displayNotification();
            }
        });


        Button cancelBtn = (Button) findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                cancelNotification();
            }
        });

        Button updateBtn = (Button) findViewById(R.id.update);
        updateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                updateNotification();
            }
        });
    }

    protected void displayNotification() {
        Log.i("Start", "notification");

        //invoking the default notification service
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setContentTitle("New MEssage");
        mBuilder.setContentText("you have recieved new message!");
        mBuilder.setTicker("New Message Alert!");
        mBuilder.setSmallIcon(R.drawable.icon);

        //increase notification number everytime a new notification arrives
        mBuilder.setNumber(++numMessages);

        // creates an explicit intent for an activity in your app
        Intent resultIntent = new Intent(this, NotificationView.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationView.class);

        //Adds te intent that starts the activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Notification id allows you to update the notification later on
        mNotificationManager.notify(notificationId, mBuilder.build());
    }


    protected void cancelNotification() {
        Log.i("Cancel", "Notification");
        mNotificationManager.cancel(notificationId);
    }

    protected void updateNotification() {
        Log.i("update", "Notification");

        //invoking the default notification service
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setContentTitle("Updated Message");
        mBuilder.setContentText("you have got updated message.");
        mBuilder.setTicker("Updated Message Alert!");
        mBuilder.setSmallIcon(R.drawable.icon);

        //increase notification number everytime a notification arrives
        mBuilder.setNumber(++numMessages);

        //create an explicit intent for an activity in your app
        Intent resultIntent = new Intent(this, NotificationView.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationView.class);


        // Adds the intent that starts the activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);


        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //update the existing Notification using the same Notification ID
        mNotificationManager.notify(notificationId, mBuilder.build());
    }
}
