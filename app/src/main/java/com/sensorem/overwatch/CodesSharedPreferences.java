package com.sensorem.overwatch;

import android.content.Context;
import android.content.SharedPreferences;

// This class will be used to save the code for login and the passcode for arm / disarm

public class CodesSharedPreferences
{

    public static final String SHARED_CODE = "sharedLoginCode";
    public static final String SHARED_PASSCODE = "sharedPasscode";
    public static final String IS_LOGGED = "isLogged";

    protected SharedPreferences sharedPreferences;

    public CodesSharedPreferences(Context context)
    {
        sharedPreferences = context.getSharedPreferences("CodesPreference",
                Context.MODE_PRIVATE );
    }

    public void setLoginCode(String code)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARED_CODE,code);
        editor.apply();
    }

    public void saveArmDisarmPasscode(String passcode)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARED_PASSCODE,passcode);
        editor.apply();
    }

    public void setIsLogged(boolean truth)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_LOGGED, truth);
        editor.apply();
    }

    public String getLoginCode()
    {
        return sharedPreferences.getString(SHARED_CODE, null);
    }

    public String getArmDisarmPasscode()
    {
        return sharedPreferences.getString(SHARED_PASSCODE, null);
    }

    public boolean getIsLogged()
    {
        return sharedPreferences.getBoolean(IS_LOGGED, false);
    }
}
