package com.sensorem.overwatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sensorem.overwatch.HistoryLogDatabase.Events;
import com.sensorem.overwatch.HistoryLogDatabase.HistoryDatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoryLogActivity extends AppCompatActivity {

    protected ListView historyListView;
    Calendar currentDateTime;

    private CodesSharedPreferences codesSharedPreferences;
    private ArmStatusSharedPreferences armStatusSharedPreferences;

    HistoryDatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_log);

        getSupportActionBar().setTitle("History Log");
        setupUI();

        loadHistoryListView();
    }

    protected void setupUI(){
        historyListView = findViewById(R.id.historyListView);
        codesSharedPreferences = new CodesSharedPreferences(HistoryLogActivity.this);
        armStatusSharedPreferences = new ArmStatusSharedPreferences(HistoryLogActivity.this);

        currentDateTime = Calendar.getInstance();
        dbhelper = new HistoryDatabaseHelper(HistoryLogActivity.this);

        dbhelper.deleteEventIf24hour(currentDateTime);
    }

    @Override
    protected void onStart(){
        super.onStart();
        dbhelper.deleteEventIf24hour(currentDateTime);
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

    public void loadHistoryListView(){

        HistoryDatabaseHelper dth = new HistoryDatabaseHelper(this);
        List<Events> eventList = dth.getAllEvents();
        ArrayList<String> listOfEvents = new ArrayList<>();


        // Display the event with the date and time associated
        for(int i=0; i< eventList.size(); i++){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String dateTime = simpleDateFormat.format(eventList.get(i).getSavedDateTime().getTime());
            listOfEvents.add("\n" + eventList.get(i).getEventName() + "\n" + "    " + dateTime + "\n");
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listOfEvents);
        historyListView.setAdapter(adapter);
    }
}
