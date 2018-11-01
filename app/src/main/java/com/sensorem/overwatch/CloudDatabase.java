package com.sensorem.overwatch;

import com.firebase.client.Firebase;

public class CloudDatabase extends android.app.Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
