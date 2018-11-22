package com.sensorem.overwatch.HistoryLogDatabase;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentTimeSharedPref {

    public static final String SHARED_CURRENT_HOUR = "sharedCurrentHour";
    public static final String SHARED_CURRENT_MINUTE = "sharedCurrentMinute";
    public static final String SHARED_CURRENT_SECOND = "sharedCurrentSecond";

    protected SharedPreferences sharedPreferences;

    public CurrentTimeSharedPref(Context context)
    {
        sharedPreferences = context.getSharedPreferences("CurrentTimePreference",
                Context.MODE_PRIVATE );
    }
    public void setCurrentHour(Calendar current)
    {
        int currentHour = current.get(Calendar.HOUR_OF_DAY);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SHARED_CURRENT_HOUR,currentHour);
        editor.apply();
    }
    public void setCurrentMinute(Calendar current)
    {
        int currentMinute = current.get(Calendar.MINUTE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SHARED_CURRENT_MINUTE,currentMinute);
        editor.apply();
    }

    public void setCurrentSecond(Calendar current)
    {
        int currentSecond = current.get(Calendar.SECOND);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SHARED_CURRENT_SECOND,currentSecond);
        editor.apply();
    }

    public int getCurrentHour()
    {
        return sharedPreferences.getInt(SHARED_CURRENT_HOUR,0);
    }

    public int getCurrentMinute()
    {
        return sharedPreferences.getInt(SHARED_CURRENT_MINUTE,0);
    }

    public int getCurrentSecond()
    {
        return sharedPreferences.getInt(SHARED_CURRENT_SECOND,0);
    }

}
