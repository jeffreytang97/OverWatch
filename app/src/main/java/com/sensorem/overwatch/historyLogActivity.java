package com.sensorem.overwatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class historyLogActivity extends AppCompatActivity {

    protected ListView historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_log);

        getSupportActionBar().setTitle("History Log");
        setupUI();
    }

    protected void setupUI(){
        historyList = findViewById(R.id.historyListView);
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
            Intent alarmIntent = new Intent(this, alarmActivity.class);
            startActivity(alarmIntent);
        }
        if (id == R.id.historyButton){
            Intent historyIntent = new Intent(this, historyLogActivity.class);
            startActivity(historyIntent);
        }
        if (id == R.id.settingsButton){
            Intent settingIntent = new Intent(this, settingActivity.class);
            startActivity(settingIntent);
        }
        if (id == R.id.logOutButton){
            // Will do in the future
            // Erase the sharedPreference object
        }

        return super.onOptionsItemSelected(item);
    }
}
