package com.sensorem.overwatch;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.sensorem.overwatch.HistoryLogDatabase.Events;
import com.sensorem.overwatch.HistoryLogDatabase.HistoryDatabaseHelper;

import java.util.Calendar;

public class AutoArmReceiverMonday extends BroadcastReceiver {

    private ArmStatusSharedPreferences armStatusSharedPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        armStatusSharedPreferences = new ArmStatusSharedPreferences(context);
        armStatusSharedPreferences.setArmStatus(1);

        HistoryDatabaseHelper dbhelper = new HistoryDatabaseHelper(context);
        Calendar currentDateTime = Calendar.getInstance();
        dbhelper.insertEvent(new Events(-1, "Alarm auto armed", currentDateTime));

        createNotificationChannel(context);
        Intent resultIntent = new Intent(context,HistoryLogActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context,1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default")
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Overwatch")
                .setContentText("Alarm Auto Armed")
                .setContentIntent(resultPendingIntent)
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
    }

    private void createNotificationChannel(Context context)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            CharSequence charSequence = "Personal Notifications";
            String description = "Include all the personal notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel("default",charSequence,importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
