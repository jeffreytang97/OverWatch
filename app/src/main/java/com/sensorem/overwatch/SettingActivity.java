package com.sensorem.overwatch;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.sensorem.overwatch.HistoryLogDatabase.CurrentTimeSharedPref;
import com.sensorem.overwatch.HistoryLogDatabase.Events;
import com.sensorem.overwatch.HistoryLogDatabase.HistoryDatabaseHelper;
import com.sensorem.overwatch.fragment.change_PIN_Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SettingActivity extends AppCompatActivity {

  private static final String TAG = "Setting Activity";

  private Button settingEditAutoButton;
  private Button settingChangePasscodeButton;

  private Button settingArmButton;
  private Button settingDisarmButton;

  private CodesSharedPreferences codesSharedPreferences;
  private ArmStatusSharedPreferences armStatusSharedPreferences;
  private SetTimeSharedPreferences setTimeSharedPreferences;
  SimpleDateFormat time = new SimpleDateFormat("HH:mm");

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_setting);

    getSupportActionBar().setTitle("Settings");

    setupUI();

  }

  @Override
  protected void onStart() {
    super.onStart();

    setupUI();

  }

  protected void setupUI(){

    settingEditAutoButton = findViewById(R.id.settingEditAutoButton);
    settingChangePasscodeButton = findViewById(R.id.settingChangePasscodeButton);

    settingArmButton = findViewById(R.id.settingAutoArm);
    settingDisarmButton = findViewById(R.id.settingAutoDisarm);

    codesSharedPreferences = new CodesSharedPreferences(SettingActivity.this);
    armStatusSharedPreferences = new ArmStatusSharedPreferences(SettingActivity.this);
    setTimeSharedPreferences = new SetTimeSharedPreferences(SettingActivity.this);

    if (armStatusSharedPreferences.getAutoArmStatus()){
      settingArmButton.setEnabled(false);
      settingDisarmButton.setEnabled(true);
    }
    else{
      settingArmButton.setEnabled(true);
      settingDisarmButton.setEnabled(false);
    }

    settingEditAutoButton.setOnClickListener(new  Button.OnClickListener(){
      @Override
      public void onClick(View v) {
        goToEditAutoTime();
      }
    });

    settingChangePasscodeButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        editPasscode();
      }
    });

    settingArmButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        if(setTimeSharedPreferences.getMondayArm().isEmpty() || setTimeSharedPreferences.getMondayDisarm().isEmpty())
        {
          Toast.makeText(SettingActivity.this, "Invalid entry was put", Toast.LENGTH_SHORT).show();
        }
        else
        {
          armStatusSharedPreferences.setAutoArmStatus(true);
          settingArmButton.setEnabled(false);
          settingDisarmButton.setEnabled(true);

          Log.d(TAG, "Enabled");
          Toast.makeText(SettingActivity.this, "Enable Automatic Arm / Disarm", Toast.LENGTH_SHORT).show();
          Calendar theTime = Calendar.getInstance();
          String time;

          Calendar c = Calendar.getInstance();

          //ARM PORTION
          time = setTimeSharedPreferences.getMondayArm();
          String[] times = time.split(":");
          theTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(times[0]));
          theTime.set(Calendar.MINUTE, Integer.valueOf(times[1]));
          theTime.set(Calendar.SECOND, 0);

          if(c.before(theTime))
          {
            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent;
            PendingIntent pendingIntent;

            intent = new Intent(getApplicationContext(), AutoArmReceiverMonday.class);
            pendingIntent = PendingIntent.getBroadcast(SettingActivity.this,0,intent,0);

            manager.set(AlarmManager.RTC_WAKEUP, theTime.getTimeInMillis(), pendingIntent);
          }

          c = Calendar.getInstance();

          //DISARM PORTION
          time = setTimeSharedPreferences.getMondayDisarm();
          times = time.split(":");
          theTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(times[0]));
          theTime.set(Calendar.MINUTE, Integer.valueOf(times[1]));
          theTime.set(Calendar.SECOND, 0);

          if(c.before(theTime))
          {
            AlarmManager disarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent disarmIntent;
            PendingIntent disarmPendingIntent;

            disarmIntent = new Intent(getApplicationContext(), AutoDisarmReceiverMonday.class);
            disarmPendingIntent = PendingIntent.getBroadcast(SettingActivity.this,0,disarmIntent,0);

            disarmManager.set(AlarmManager.RTC_WAKEUP, theTime.getTimeInMillis(),disarmPendingIntent);
          }
        }



      }
    });

    settingDisarmButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        armStatusSharedPreferences.setAutoArmStatus(false);
        settingArmButton.setEnabled(true);
        settingDisarmButton.setEnabled(false);

        Log.d(TAG, "Disable");
        Toast.makeText(SettingActivity.this, "Disable Automatic Arm / Disarm", Toast.LENGTH_SHORT).show();

        //ARM PORTION
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent;
        PendingIntent pendingIntent;

        intent = new Intent(getApplicationContext(), AutoArmReceiverMonday.class);
        pendingIntent = PendingIntent.getBroadcast(SettingActivity.this,0,intent,0);

        manager.cancel(pendingIntent);

        //DISARM PORTION
        AlarmManager disarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent disarmIntent;
        PendingIntent disarmPendingIntent;

        disarmIntent = new Intent(getApplicationContext(), AutoDisarmReceiverMonday.class);
        disarmPendingIntent = PendingIntent.getBroadcast(SettingActivity.this,0,disarmIntent,0);

        disarmManager.cancel(disarmPendingIntent);

      }
    });

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
      armStatusSharedPreferences.setArmStatus(0);
      Intent logoutIntent = new Intent(this, MainActivity.class);
      startActivity(logoutIntent);
    }

    return super.onOptionsItemSelected(item);
  }


  public void goToEditAutoTime(){
    Intent intent = new Intent(this, SetAutomaticTime.class);
    startActivity(intent);
  }

  public void editPasscode(){
    change_PIN_Fragment dialog = new change_PIN_Fragment();
    dialog.show(getSupportFragmentManager(), "Edit passcode.");
  }
}
