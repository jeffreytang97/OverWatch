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

public class CloudDatabase extends android.app.Application {

    private static final String TAG = "FIREBASE";

    Firebase doorRef, motionRef;
    SensorsStatus status;

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);

        doorRef = new Firebase("https://iotalarmsystem.firebaseio.com/doorOpen");
        motionRef = new Firebase("https://iotalarmsystem.firebaseio.com/motionDetected");
        status = new SensorsStatus(CloudDatabase.this);
        getSensorStatus();
    }

    public void getSensorStatus(){

        doorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                Log.d(TAG, "Door value changed");
                Boolean isDoorOpen = ds.getValue(Boolean.class);
                status.setDoorOpened(isDoorOpen);

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

        if(status.getMotionDetected() && status.getDoorOpened())
            isTriggered = true;
        else
            isTriggered = false;

        return isTriggered;
    }
}
