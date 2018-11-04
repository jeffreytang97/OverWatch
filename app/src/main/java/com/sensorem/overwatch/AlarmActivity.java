package com.sensorem.overwatch;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmActivity extends AppCompatActivity {

    protected Switch theSwitch;
    protected TextView movementTextView, doorTextView;
    protected String statusDoor, statusMotion;
    private CodesSharedPreferences codesSharedPreferences;
    private SensorsStatus sensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        getSupportActionBar().setTitle("Alarm Activity");
        setupUI();
        setupSwitch();

        doorStatusDisplay();
        motionStatusDisplay();
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    protected void setupUI(){
        theSwitch = findViewById(R.id.alarmSwitch);
        movementTextView = findViewById(R.id.movementDetectorTextView);
        doorTextView = findViewById(R.id.doorStatusTextView);
        codesSharedPreferences = new CodesSharedPreferences(AlarmActivity.this);
        sensors = new SensorsStatus(AlarmActivity.this);
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
            Intent logoutIntent = new Intent(this, MainActivity.class);
            startActivity(logoutIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void setupSwitch()
    {
        theSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked) Toast.makeText(AlarmActivity.this, "Alarm On", Toast.LENGTH_SHORT).show();
                if(!isChecked) Toast.makeText(AlarmActivity.this, "Alarm Off", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
