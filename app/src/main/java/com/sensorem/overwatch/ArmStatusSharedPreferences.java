package com.sensorem.overwatch;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class ArmStatusSharedPreferences
{
  private static final String TAG = "ArmStatusPreferences";

  private static final String IS_ARMED = "isArmed";

  protected SharedPreferences sharedPreferences;

  public ArmStatusSharedPreferences(Context context)
  {
    sharedPreferences = context.getSharedPreferences("ArmStatus", Context.MODE_PRIVATE);
  }

  public boolean getArmStatus()
  {
    return sharedPreferences.getBoolean(IS_ARMED,false);
  }

  public void setArmStatus(boolean status)
  {
    Log.d(TAG, "Arm Status set to: "+ status);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putBoolean(IS_ARMED, status);
    editor.apply();
  }
}
