package com.sensorem.overwatch;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class ArmStatusSharedPreferences
{
  private static final String TAG = "ArmStatusPreferences";

  private static final String IS_ARMED = "isArmed";

  private static final String IS_AUTO_ARM = "isAutoArmed";

  protected SharedPreferences sharedPreferences;

  public ArmStatusSharedPreferences(Context context)
  {
    sharedPreferences = context.getSharedPreferences("ArmStatus", Context.MODE_PRIVATE);
  }

  //Modes are now in integers; 0 = Disarmed; 1 = Armed; 2 = Home
  public int getArmStatus()
  {
    return sharedPreferences.getInt(IS_ARMED,-1);
  }

  public void setArmStatus(int status)
  {
    Log.d(TAG, "Arm Status set to: "+ status);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putInt(IS_ARMED, status);
    editor.apply();
  }

  public boolean getAutoArmStatus(){
    return sharedPreferences.getBoolean(IS_AUTO_ARM, false);
  }

  public void setAutoArmStatus(boolean status){
    Log.d(TAG, "Auto Arm set to: " + status);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putBoolean(IS_AUTO_ARM, status);
    editor.apply();
  }

}
