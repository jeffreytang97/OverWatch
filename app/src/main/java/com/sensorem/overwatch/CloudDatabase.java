package com.sensorem.overwatch;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class CloudDatabase extends android.app.Application {

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
                Boolean isDoorOpen = ds.getValue(Boolean.class);
                status.setDoorOpened(isDoorOpen);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // read failed
            }
        });

        motionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                Boolean isMotionDetected = ds.getValue(Boolean.class);
                status.setMotionDetected(isMotionDetected);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // read failed
            }
        });
    }
}
