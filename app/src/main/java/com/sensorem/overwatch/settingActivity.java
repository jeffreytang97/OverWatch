package com.sensorem.overwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class settingActivity extends AppCompatActivity {

    private TextView settingArmTextView;
    private TextView settingDisarmTextView;
    private TextView settingArmTimeTextView;
    private TextView settingDisarmTimeTextView;
    private Button settingEditArmTimeButton;
    private Button settingEditDisarmTimeButton;
    private Switch settingEditSwitch;
    private Button settingChangePasscodeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

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

    }
}
