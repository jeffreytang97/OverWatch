package com.sensorem.overwatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected EditText codeEditText;
    protected Button loginButton;
    public String theCode = "jeff131313";
    protected String armCode;

    private codesSharedPreferences sharedPrefsLoginCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
    }

    protected void onStart()
    {
        super.onStart();

        // When the user log out, the code data will be erased from the sharedPreferences
        if(sharedPrefsLoginCode.getIsLogged()==true)
            goToAlarmActivity();
    }

    protected void onResume(){
        super.onResume();
    }

    protected void setupUI(){

        // initialize shared preference object
        sharedPrefsLoginCode = new codesSharedPreferences(MainActivity.this);

        codeEditText = findViewById(R.id.codeEditText);
        loginButton = findViewById(R.id.loginButton);
        //sharedPrefsLoginCode.setLoginCode(theCode); // save the code somewhere for future feature (change code example) NOT NEEDED

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAlarmActivityCondition();
            }
        });
    }

    public void goToAlarmActivity(){
        Intent intent = new Intent(MainActivity.this, alarmActivity.class);
        startActivity(intent);
    }

    public void goToAlarmActivityCondition(){

        //String code = sharedPrefsLoginCode.getLoginCode(); NO NEED

        if (codeEditText.getText().toString().equals(theCode)){
            sharedPrefsLoginCode.setIsLogged(true);
            Intent loginIntent = new Intent(MainActivity.this, alarmActivity.class);
            startActivity(loginIntent);
        }
        else{
            Toast.makeText(this, "Invalid code.", Toast.LENGTH_LONG).show();
        }
    }
}
