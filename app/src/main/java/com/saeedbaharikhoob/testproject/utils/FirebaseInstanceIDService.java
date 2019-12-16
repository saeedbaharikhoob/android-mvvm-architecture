package com.saeedbaharikhoob.testproject.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.view.MainActivity;

import java.util.Random;

public class FirebaseInstanceIDService extends FirebaseMessagingService {

    private static final String TAG = "saeed=>";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }


    }

    private void sendNotification(String title, String messageBody) {

        Random r = new Random();
        int i1 = r.nextInt(200 - 1) + 1;
        String CHANNEL_ID = getString(R.string.default_notification_channel_id);
        CharSequence name = getString(R.string.channel_name);
        int importance = NotificationManager.IMPORTANCE_HIGH;


        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationBuilder.setSmallIcon(R.drawable.ic_buy_on)
                    .setColor(getResources().getColor(R.color.colorPrimary))
                    .setContentTitle(title)
                    .setAutoCancel(true)
                    .setContentText(messageBody)
                    .setChannelId(CHANNEL_ID);
            notificationBuilder.setContentIntent(pendingIntent);

            notificationManager.createNotificationChannel(mChannel);

            notificationManager.notify(i1, notificationBuilder.build());
        } else {
            notificationBuilder.setSmallIcon(R.drawable.ic_small_notification)
                    .setColor(getResources().getColor(R.color.colorPrimary))
                    .setContentTitle(title)
                    .setAutoCancel(true)
                    .setContentText(messageBody)
                    .setPriority(Notification.PRIORITY_HIGH);
            notificationBuilder.setContentIntent(pendingIntent);

            notificationManager.notify(i1, notificationBuilder.build());
        }
    }

    @Override
    public void onNewToken(String token) {

    }


}
