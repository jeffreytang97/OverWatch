package com.sensorem.overwatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sensorem.overwatch.HistoryLogDatabase.CurrentTimeSharedPref;
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
    private CurrentTimeSharedPref currentTimeSharedPref;

    HistoryDatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_log);

        getSupportActionBar().setTitle("History Log");
        setupUI();

        if(codesSharedPreferences.getIsLogged()){
            loadHistoryListView();
        }
    }

    protected void setupUI(){
        historyListView = findViewById(R.id.historyListView);
        codesSharedPreferences = new CodesSharedPreferences(HistoryLogActivity.this);
        armStatusSharedPreferences = new ArmStatusSharedPreferences(HistoryLogActivity.this);
        currentTimeSharedPref = new CurrentTimeSharedPref(HistoryLogActivity.this);

        currentDateTime = Calendar.getInstance();
        currentTimeSharedPref.setCurrentHour(currentDateTime);
        currentTimeSharedPref.setCurrentMinute(currentDateTime);
        currentTimeSharedPref.setCurrentSecond(currentDateTime);

        dbhelper = new HistoryDatabaseHelper(HistoryLogActivity.this);

        dbhelper.deleteEventIf24hour(currentDateTime);
    }

    @Override
    protected void onStart(){
        super.onStart();
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
            loadHistoryListView();
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
