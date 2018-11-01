package com.sensorem.overwatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.sensorem.overwatch.fragment.arm_alarm_Fragment;
import com.sensorem.overwatch.fragment.change_PIN_Fragment;
import com.sensorem.overwatch.fragment.disarm_alarm_fragment;

public class SettingActivity extends AppCompatActivity {

    private TextView settingArmTextView;
    private TextView settingDisarmTextView;
    private TextView settingArmTimeTextView;
    private TextView settingDisarmTimeTextView;
    private Button settingEditArmTimeButton;
    private Button settingEditDisarmTimeButton;
    private Switch settingEditSwitch;
    private Button settingChangePasscodeButton;

    private CodesSharedPreferences codesSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportActionBar().setTitle("Settings");

        setupUI();

    }

    protected void setupUI(){

        settingArmTextView = findViewById(R.id.settingArmTextView);
        settingDisarmTextView = findViewById(R.id.settingDisarmTextView);
        settingArmTimeTextView = findViewById(R.id.settingArmTimeTextView);
        settingDisarmTimeTextView = findViewById(R.id.settingDisarmTimeTextView);
        settingEditArmTimeButton = findViewById(R.id.settingEditArmTimeButton);
        settingEditDisarmTimeButton = findViewById(R.id.settingEditDisarmTimeButton);
        settingEditSwitch = findViewById(R.id.settingEditSwitch);
        settingChangePasscodeButton = findViewById(R.id.settingChangePasscodeButton);

        codesSharedPreferences = new CodesSharedPreferences(SettingActivity.this);

        settingEditArmTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editArmTime();
            }
        });

        settingEditDisarmTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDisarmTime();
            }
        });

        settingChangePasscodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPasscode();
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
            Intent logoutIntent = new Intent(this, MainActivity.class);
            startActivity(logoutIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void editArmTime(){
        arm_alarm_Fragment dialog = new arm_alarm_Fragment();
        dialog.show(getSupportFragmentManager(), "Edit Arm time.");
    }

    public void editDisarmTime(){
        disarm_alarm_fragment dialog = new disarm_alarm_fragment();
        dialog.show(getSupportFragmentManager(), "Edit Disarm time.");
    }

    public void editPasscode(){
        change_PIN_Fragment dialog = new change_PIN_Fragment();
        dialog.show(getSupportFragmentManager(), "Edit passcode.");
    }
}
