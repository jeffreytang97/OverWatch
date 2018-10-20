package com.sensorem.overwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class alarmActivity extends AppCompatActivity {

    protected Switch theSwitch;
    protected TextView movementText;
    protected TextView doorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        getSupportActionBar().setTitle("OverWatch Main");

        setupUI();
    }

    protected void setupUI(){
        theSwitch = findViewById(R.id.alarmSwitch);
        movementText = findViewById(R.id.movementDetectorTextView);
        doorText = findViewById(R.id.doorStatusTextView);
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

        return super.onOptionsItemSelected(item);
    }
}
