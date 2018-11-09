package com.sensorem.overwatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.sensorem.overwatch.fragment.change_PIN_Fragment;

public class SettingActivity extends AppCompatActivity {

    private Button settingEditAutoButton;
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

        settingEditAutoButton = findViewById(R.id.settingEditAutoButton);
        settingEditSwitch = findViewById(R.id.settingEditSwitch);
        settingChangePasscodeButton = findViewById(R.id.settingChangePasscodeButton);

        codesSharedPreferences = new CodesSharedPreferences(SettingActivity.this);

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


    public void goToEditAutoTime(){
        Intent intent = new Intent(this, SetAutomaticTime.class);
        startActivity(intent);
    }

    public void editPasscode(){
        change_PIN_Fragment dialog = new change_PIN_Fragment();
        dialog.show(getSupportFragmentManager(), "Edit passcode.");
    }
}
