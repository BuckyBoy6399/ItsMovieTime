package com.example.rajraval.itsmovietime;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("Notification",remoteMessage.getNotification().getBody()+" "
                +remoteMessage.getNotification().getTitle());
        Intent i =new Intent(this,homescreen.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent p= PendingIntent.getActivity(getApplicationContext(),0,i,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.abc)
                .setContentTitle("ItsMovieTime")
                .setContentText(remoteMessage.getNotification().getBody())
                .setContentIntent(p);
        NotificationManager nm= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (nm != null) {
            nm.notify(0,builder.build());
        }

    }


}
