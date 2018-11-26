package com.sensorem.overwatch;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sensorem.overwatch.HistoryLogDatabase.CurrentTimeSharedPref;
import com.sensorem.overwatch.HistoryLogDatabase.Events;
import com.sensorem.overwatch.HistoryLogDatabase.HistoryDatabaseHelper;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    private static final String TAG = "Alarm Activity";

    protected Button armButton, disarmButton, homeButton;
    protected TextView movementTextView, doorTextView, alarmStatusTextView;
    protected String statusDoor, statusMotion;
    private CodesSharedPreferences codesSharedPreferences;
    private SensorsStatus sensors;
    private ArmStatusSharedPreferences armStatusSharedPreferences;
    private SetTimeSharedPreferences setTimeSharedPreferences;

    private CurrentTimeSharedPref currentTimeSharedPref;
    public Calendar currentDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        getSupportActionBar().setTitle("Alarm Activity");
        setupUI();
        setupButtons();

        doorStatusDisplay();
        motionStatusDisplay();
        setCurrentTime();
    }

    @Override
    protected void onStart(){
        super.onStart();
    }


    protected void setCurrentTime(){

        currentDateTime = Calendar.getInstance();
        currentTimeSharedPref.setCurrentHour(currentDateTime);
        currentTimeSharedPref.setCurrentMinute(currentDateTime);
        currentTimeSharedPref.setCurrentSecond(currentDateTime);
    }

    protected void setupUI(){
        //theSwitch = findViewById(R.id.alarmSwitch);
        armButton = findViewById(R.id.armButton);
        disarmButton = findViewById(R.id.diarmButton);
        homeButton = findViewById(R.id.stayHomeButton);
        movementTextView = findViewById(R.id.movementDetectorTextView);
        doorTextView = findViewById(R.id.doorStatusTextView);
        alarmStatusTextView = findViewById(R.id.alarmStatusTextView);
        codesSharedPreferences = new CodesSharedPreferences(AlarmActivity.this);
        sensors = new SensorsStatus(AlarmActivity.this);
        armStatusSharedPreferences = new ArmStatusSharedPreferences(AlarmActivity.this);
        currentTimeSharedPref = new CurrentTimeSharedPref(AlarmActivity.this);
        setTimeSharedPreferences = new SetTimeSharedPreferences(AlarmActivity.this);

        if(armStatusSharedPreferences.getArmStatus() == 1)
        {
            alarmStatusTextView.setText("Alarm is Armed");
            armButton.setEnabled(false);
            disarmButton.setEnabled(true);
            homeButton.setEnabled(true);
        }
        else if(armStatusSharedPreferences.getArmStatus() == 0)
        {
            alarmStatusTextView.setText("Alarm is Disarmed");
            armButton.setEnabled(true);
            disarmButton.setEnabled(false);
            homeButton.setEnabled(true);
        }
        else if(armStatusSharedPreferences.getArmStatus() == 2)
        {
            alarmStatusTextView.setText("Alarm is in Home Mode");
            armButton.setEnabled(true);
            disarmButton.setEnabled(true);
            homeButton.setEnabled(false);
        }

    }

    public void doorStatusDisplay(){

        // This part is only for testing purpose with the cloud for now

        if (sensors.getDoorOpened()){
            statusDoor = "The door is currently opened.";
            doorTextView.setText(statusDoor);
        }
        else{
            statusDoor = "The door is currently closed.";
            doorTextView.setText(statusDoor);
        }
    }

    public void motionStatusDisplay(){

        // This part is only for testing purpose with the cloud for now

        if (sensors.getMotionDetected()){
            statusMotion = "Movement detected.";
            movementTextView.setText(statusMotion);
        }
        else{
            statusMotion = "No movement detected.";
            movementTextView.setText(statusMotion);
        }
    }

    //To show the 3 dots button on the action bar
    @Override
    public boolean onCreateOptionsMenu (Menu menu){

        //inflate menu
        getMenuInflater().inflate(R.menu.history_log_menu, menu);
        return true;
    }

    // Function to handle the toggle button
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        // Menu item click handling

        if (id == R.id.alarmActivityButton_log){
            Intent alarmIntent = new Intent(this, AlarmActivity.class);
            startActivity(alarmIntent);
        }
        if (id == R.id.historyButton_log){
            Intent historyIntent = new Intent(this, HistoryLogActivity.class);
            startActivity(historyIntent);
        }
        if (id == R.id.settingsButton_log){
            Intent settingIntent = new Intent(this, SettingActivity.class);
            startActivity(settingIntent);
        }
        if (id == R.id.logOutButton_log){
            codesSharedPreferences.setIsLogged(false);
            armStatusSharedPreferences.setArmStatus(0);
            Intent logoutIntent = new Intent(this, MainActivity.class);
            startActivity(logoutIntent);
        }
        if (id == R.id.refreshButton_log){
            doorStatusDisplay();
            motionStatusDisplay();
            setCurrentTime();
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

                armButton.setEnabled(false);
                disarmButton.setEnabled(true);
                homeButton.setEnabled(true);
                Log.d(TAG, "Alarm Armed");
                Toast.makeText(AlarmActivity.this, "Alarm Armed", Toast.LENGTH_SHORT).show();
                armStatusSharedPreferences.setArmStatus(1);
                alarmStatusTextView.setText("Alarm is Armed");


                HistoryDatabaseHelper dbhelper = new HistoryDatabaseHelper(AlarmActivity.this);
                currentDateTime = Calendar.getInstance();
                dbhelper.insertEvent(new Events(-1, "Alarm armed by user", currentDateTime));

            }
        });

        disarmButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Calendar presentTime = Calendar.getInstance();
                Calendar armTime = Calendar.getInstance();
                Calendar disarmTime = Calendar.getInstance();

                String time = setTimeSharedPreferences.getMondayArm();
                String[] times = time.split(":");
                if(!time.isEmpty())
                {
                    armTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(times[0]));
                    armTime.set(Calendar.MINUTE, Integer.valueOf(times[1]));
                    armTime.set(Calendar.SECOND, 0);
                }
                else
                {
                    armTime.add(Calendar.DATE,1);
                }


                time = setTimeSharedPreferences.getMondayDisarm();
                times = time.split(":");
                if(!time.isEmpty())
                {
                    disarmTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(times[0]));
                    disarmTime.set(Calendar.MINUTE, Integer.valueOf(times[1]));
                    disarmTime.set(Calendar.SECOND, 0);
                }
                else
                {
                    disarmTime.add(Calendar.DATE,-1);
                }





                if(armTime.before(presentTime) && presentTime.before(disarmTime) && armStatusSharedPreferences.getAutoArmStatus())
                {
                    armButton.setEnabled(true);
                    disarmButton.setEnabled(false);
                    homeButton.setEnabled(true);
                    Log.d(TAG, "Alarm Disarmed");
                    Toast.makeText(AlarmActivity.this, "Alarm Disarmed and Auto Scheduling disabled", Toast.LENGTH_LONG).show();
                    armStatusSharedPreferences.setArmStatus(0);
                    armStatusSharedPreferences.setAutoArmStatus(false);
                    alarmStatusTextView.setText("Alarm is Disarmed");

                    HistoryDatabaseHelper dbhelper = new HistoryDatabaseHelper(AlarmActivity.this);
                    currentDateTime = Calendar.getInstance();
                    dbhelper.insertEvent(new Events(-1, "Override of automatic scheduling and Alarm disarmed by user", currentDateTime));

                    AlarmManager disarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    Intent disarmIntent;
                    PendingIntent disarmPendingIntent;

                    disarmIntent = new Intent(getApplicationContext(), AutoDisarmReceiverMonday.class);
                    disarmPendingIntent = PendingIntent.getBroadcast(AlarmActivity.this,0,disarmIntent,0);

                    disarmManager.cancel(disarmPendingIntent);

                }
                else{
                    armButton.setEnabled(true);
                    disarmButton.setEnabled(false);
                    homeButton.setEnabled(true);
                    Log.d(TAG, "Alarm Disarmed");
                    Toast.makeText(AlarmActivity.this, "Alarm Disarmed", Toast.LENGTH_SHORT).show();
                    armStatusSharedPreferences.setArmStatus(0);
                    alarmStatusTextView.setText("Alarm is Disarmed");

                    HistoryDatabaseHelper dbhelper = new HistoryDatabaseHelper(AlarmActivity.this);
                    currentDateTime = Calendar.getInstance();
                    dbhelper.insertEvent(new Events(-1, "Alarm disarmed by user", currentDateTime));
                }
            }

        });

        homeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Calendar presentTime = Calendar.getInstance();
                Calendar armTime = Calendar.getInstance();
                Calendar disarmTime = Calendar.getInstance();

                String time = setTimeSharedPreferences.getMondayArm();
                String[] times = time.split(":");
                if(!time.isEmpty())
                {
                    armTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(times[0]));
                    armTime.set(Calendar.MINUTE, Integer.valueOf(times[1]));
                    armTime.set(Calendar.SECOND, 0);
                }
                else
                {
                    armTime.add(Calendar.DATE,1);
                }

                time = setTimeSharedPreferences.getMondayDisarm();
                times = time.split(":");
                if(!time.isEmpty())
                {
                    disarmTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(times[0]));
                    disarmTime.set(Calendar.MINUTE, Integer.valueOf(times[1]));
                    disarmTime.set(Calendar.SECOND, 0);
                }
                else
                {
                    disarmTime.add(Calendar.DATE,-1);
                }


                if(armTime.before(presentTime) && presentTime.before(disarmTime) && armStatusSharedPreferences.getAutoArmStatus()){

                    armButton.setEnabled(true);
                    disarmButton.setEnabled(true);
                    homeButton.setEnabled(false);
                    Log.d(TAG, "Alarm Home Mode");
                    Toast.makeText(AlarmActivity.this, "Home Mode and Auto Scheduling disabled", Toast.LENGTH_LONG).show();
                    armStatusSharedPreferences.setArmStatus(2);
                    armStatusSharedPreferences.setAutoArmStatus(false);
                    alarmStatusTextView.setText("Alarm is in Home Mode");

                    HistoryDatabaseHelper dbhelper = new HistoryDatabaseHelper(AlarmActivity.this);
                    currentDateTime = Calendar.getInstance();
                    dbhelper.insertEvent(new Events(-1, "Override of automatic scheduling and Alarm put in Home Mode by user", currentDateTime));

                    AlarmManager disarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    Intent disarmIntent;
                    PendingIntent disarmPendingIntent;

                    disarmIntent = new Intent(getApplicationContext(), AutoDisarmReceiverMonday.class);
                    disarmPendingIntent = PendingIntent.getBroadcast(AlarmActivity.this,0,disarmIntent,0);

                    disarmManager.cancel(disarmPendingIntent);

                }
                else{
                    armButton.setEnabled(true);
                    disarmButton.setEnabled(true);
                    homeButton.setEnabled(false);
                    Log.d(TAG, "Alarm Home Mode");
                    Toast.makeText(AlarmActivity.this, "Home Mode", Toast.LENGTH_SHORT).show();
                    armStatusSharedPreferences.setArmStatus(2);
                    alarmStatusTextView.setText("Alarm is in Home Mode");

                    HistoryDatabaseHelper dbhelper = new HistoryDatabaseHelper(AlarmActivity.this);
                    currentDateTime = Calendar.getInstance();
                    dbhelper.insertEvent(new Events(-1, "Alarm put in home mode by user", currentDateTime));
                }

            }
        });


    }

}
