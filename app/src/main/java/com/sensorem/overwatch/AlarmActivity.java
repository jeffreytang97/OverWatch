package com.sensorem.overwatch;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.sensorem.overwatch.HistoryLogDatabase.Events;
import com.sensorem.overwatch.HistoryLogDatabase.HistoryDatabaseHelper;

import java.util.Calendar;
import java.util.Collections;

public class AlarmActivity extends AppCompatActivity {

    private static final String TAG = "Alarm Activity";

    protected Button armButton, disarmButton;
    protected TextView movementTextView, doorTextView, alarmStatusTextView;
    protected String statusDoor, statusMotion;
    private CodesSharedPreferences codesSharedPreferences;
    private SensorsStatus sensors;
    private ArmStatusSharedPreferences armStatusSharedPreferences;

    Calendar currentDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        getSupportActionBar().setTitle("Alarm Activity");
        setupUI();
        setupButtons();

        doorStatusDisplay();
        motionStatusDisplay();

        currentDateTime = Calendar.getInstance();
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    protected void setupUI(){
        //theSwitch = findViewById(R.id.alarmSwitch);
        armButton = findViewById(R.id.armButton);
        disarmButton = findViewById(R.id.diarmButton);
        movementTextView = findViewById(R.id.movementDetectorTextView);
        doorTextView = findViewById(R.id.doorStatusTextView);
        alarmStatusTextView = findViewById(R.id.alarmStatusTextView);
        codesSharedPreferences = new CodesSharedPreferences(AlarmActivity.this);
        sensors = new SensorsStatus(AlarmActivity.this);
        armStatusSharedPreferences = new ArmStatusSharedPreferences(AlarmActivity.this);

        if(armStatusSharedPreferences.getArmStatus())
            alarmStatusTextView.setText("Alarm is Armed");
        else
            alarmStatusTextView.setText("Alarm is Disarmed");
    }

    public void doorStatusDisplay(){

        // This part is only for testing purpose with the cloud for now

        if (sensors.getDoorOpened()){
            statusDoor = "The door is currently opened. (true)";
            doorTextView.setText(statusDoor);
        }
        else{
            statusDoor = "The door is currently closed. (false)";
            doorTextView.setText(statusDoor);
        }
    }

    public void motionStatusDisplay(){

        // This part is only for testing purpose with the cloud for now

        if (sensors.getMotionDetected()){
            statusMotion = "Movement detected. (true)";
            movementTextView.setText(statusMotion);
        }
        else{
            statusMotion = "No movement detected. (false)";
            movementTextView.setText(statusMotion);
        }
    }

    //To show the 3 dots button on the action bar
    @Override
    public boolean onCreateOptionsMenu (Menu menu){

        //inflate menu
        getMenuInflater().inflate(R.menu.three_dots_drop_down, menu);
        return true;
    }

    // Function to handle the toggle button
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        // Menu item click handling

        if (id == R.id.alarmActivityButton){
            Intent alarmIntent = new Intent(this, AlarmActivity.class);
            startActivity(alarmIntent);
        }
        if (id == R.id.historyButton){
            Intent historyIntent = new Intent(this, HistoryLogActivity.class);
            startActivity(historyIntent);
        }
        if (id == R.id.settingsButton){
            Intent settingIntent = new Intent(this, SettingActivity.class);
            startActivity(settingIntent);
        }
        if (id == R.id.logOutButton){
            codesSharedPreferences.setIsLogged(false);
            armStatusSharedPreferences.setArmStatus(false);
            Intent logoutIntent = new Intent(this, MainActivity.class);
            startActivity(logoutIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void setupButtons()
    {
        armButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d(TAG, "Alarm Armed");
                Toast.makeText(AlarmActivity.this, "Alarm Armed", Toast.LENGTH_SHORT).show();
                armStatusSharedPreferences.setArmStatus(true);
                alarmStatusTextView.setText("Alarm is Armed");


                HistoryDatabaseHelper dbhelper = new HistoryDatabaseHelper(AlarmActivity.this);
                dbhelper.insertEvent(new Events(-1, "Alarm armed by user", currentDateTime));
            }
        });

        disarmButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d(TAG, "Alarm Disarmed");
                Toast.makeText(AlarmActivity.this, "Alarm Disarmed", Toast.LENGTH_SHORT).show();
                armStatusSharedPreferences.setArmStatus(false);
                alarmStatusTextView.setText("Alarm is Disarmed");

                HistoryDatabaseHelper dbhelper = new HistoryDatabaseHelper(AlarmActivity.this);
                dbhelper.insertEvent(new Events(-1, "Alarm disarmed by user", currentDateTime));
            }
        });

    }

}
