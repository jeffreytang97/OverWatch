package com.sensorem.overwatch;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.sensorem.overwatch.HistoryLogDatabase.Events;
import com.sensorem.overwatch.HistoryLogDatabase.HistoryDatabaseHelper;

import java.util.Calendar;

public class CloudDatabase extends android.app.Application {

    private static final String TAG = "FIREBASE";

    Firebase doorRef, motionRef;
    SensorsStatus status;
    ArmStatusSharedPreferences armStatusSharedPreferences;
    Calendar currentDateTime;

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);

        doorRef = new Firebase("https://iotalarmsystem.firebaseio.com/doorOpen");
        motionRef = new Firebase("https://iotalarmsystem.firebaseio.com/motionDetected");
        status = new SensorsStatus(CloudDatabase.this);
        armStatusSharedPreferences = new ArmStatusSharedPreferences(CloudDatabase.this);
        getSensorStatus();
    }

    public void getSensorStatus(){

        doorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                Log.d(TAG, "Door value changed");
                Boolean isDoorOpen = ds.getValue(Boolean.class);
                status.setDoorOpened(isDoorOpen);

                if (status.getDoorOpened()){
                    HistoryDatabaseHelper dbhelper = new HistoryDatabaseHelper(CloudDatabase.this);
                    currentDateTime = Calendar.getInstance();
                    dbhelper.insertEvent(new Events(-1, "Door has been opened", currentDateTime));
                }
                else{
                    HistoryDatabaseHelper dbhelper = new HistoryDatabaseHelper(CloudDatabase.this);
                    currentDateTime = Calendar.getInstance();
                    dbhelper.insertEvent(new Events(-1, "Door has been closed", currentDateTime));
                }

                if(isAlarmTriggered())
                    startAlarm();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // read failed
            }
        });

        motionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
              Log.d(TAG, "Movement value changed");
              Boolean isMotionDetected = ds.getValue(Boolean.class);
              status.setMotionDetected(isMotionDetected);

              if (status.getMotionDetected()){
                  HistoryDatabaseHelper dbhelper = new HistoryDatabaseHelper(CloudDatabase.this);
                  currentDateTime = Calendar.getInstance();
                  dbhelper.insertEvent(new Events(-1, "Movement detected", currentDateTime));
              }

              if(isAlarmTriggered())
                  startAlarm();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // read failed
            }
        });
    }

    private void startAlarm()
    {
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent;
        PendingIntent pendingIntent;

        intent = new Intent(getApplicationContext(), AlarmTriggerReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        manager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+1000,pendingIntent);

    }

    private boolean isAlarmTriggered()
    {
        boolean isTriggered;

        boolean sensorStatuses = (status.getMotionDetected() && status.getDoorOpened());
        boolean armStatus = armStatusSharedPreferences.getArmStatus();

        if(sensorStatuses && armStatus)
            isTriggered = true;
        else
            isTriggered = false;

        return isTriggered;
    }
}
