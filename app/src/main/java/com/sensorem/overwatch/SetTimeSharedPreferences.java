package com.sensorem.overwatch;

import android.content.Context;
import android.content.SharedPreferences;

public class SetTimeSharedPreferences {

    public static final String MONDAY_ARM = "ArmMondayTime";
    public static final String MONDAY_DISARM = "DisarmMondayTime";

    public static final String TUESDAY_ARM = "ArmTuesdayTime";
    public static final String TUESDAY_DISARM = "DisarmTuesdayTime";

    public static final String WEDNESDAY_ARM = "ArmWednesdayTime";
    public static final String WEDNESDAY_DISARM = "DisarmWednesdayTime";

    public static final String THURSDAY_ARM = "ArmThursdayTime";
    public static final String THURSDAY_DISARM = "DisarmThursdayTime";

    public static final String FRIDAY_ARM = "ArmFridayTime";
    public static final String FRIDAY_DISARM = "DisarmFridayTime";

    public static final String SATURDAY_ARM = "ArmSaturdayTime";
    public static final String SATURDAY_DISARM = "DisarmSaturdayTime";

    public static final String SUNDAY_ARM = "ArmSundayTime";
    public static final String SUNDAY_DISARM = "DisarmSundayTime";

    protected SharedPreferences sharedPreferences;

    public SetTimeSharedPreferences (Context context){
        sharedPreferences = context.getSharedPreferences("SetTimeSharedPreferences", context.MODE_PRIVATE);
    }


    /*SET FUNCTIONS
     * SET FUNCTIONS
     * SET FUNCTIONS
     * SET FUNCTIONS*/

    public void setMondayArm (String timeString){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MONDAY_ARM, timeString);
        editor.apply();
    }

    public void setMondayDisarm (String timeString){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MONDAY_DISARM, timeString);
        editor.apply();
    }

    public void setTuesdayArm (String timeString){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TUESDAY_ARM, timeString);
        editor.apply();
    }

    public void setTuesdayDisarm (String timeString){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TUESDAY_DISARM, timeString);
        editor.apply();
    }

    public void setWednesdayArm (String timeString){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(WEDNESDAY_ARM, timeString);
        editor.apply();
    }

    public void setWednesdayDisarm (String timeString){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(WEDNESDAY_DISARM, timeString);
        editor.apply();
    }

    public void setThursdayArm (String timeString){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(THURSDAY_ARM, timeString);
        editor.apply();
    }

    public void setThursdayDisarm (String timeString){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(THURSDAY_DISARM, timeString);
        editor.apply();
    }

    public void setFridayArm (String timeString){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FRIDAY_ARM, timeString);
        editor.apply();
    }

    public void setFridayDisarm (String timeString){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FRIDAY_DISARM, timeString);
        editor.apply();
    }

    public void setSaturdayArm (String timeString){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SATURDAY_ARM, timeString);
        editor.apply();
    }

    public void setSaturdayDisarm (String timeString){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SATURDAY_DISARM, timeString);
        editor.apply();
    }

    public void setSundayArm (String timeString){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SUNDAY_ARM, timeString);
        editor.apply();
    }

    public void setSundayDisarm (String timeString){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SUNDAY_DISARM, timeString);
        editor.apply();
    }

    /*GET FUNCTIONS
     * GET FUNCTIONS
     * GET FUNCTIONS
     * GET FUNCTIONS*/

    public String getMondayArm (){
        return sharedPreferences.getString(MONDAY_ARM, "");
    }

    public String getMondayDisarm(){
        return sharedPreferences.getString(MONDAY_DISARM,"");
    }

    public String getTuesdayArm(){
        return sharedPreferences.getString(TUESDAY_ARM, "");
    }

    public String getTuesdayDisarm (){
        return sharedPreferences.getString(TUESDAY_DISARM, "");
    }

    public String getWednesdayArm (){
        return sharedPreferences.getString(WEDNESDAY_ARM, "");
    }

    public String getWednesdayDisarm (){
        return sharedPreferences.getString(WEDNESDAY_DISARM, "");
    }

    public String getThursdayArm (){
        return sharedPreferences.getString(THURSDAY_ARM, "");
    }

    public String getThursdayDisarm (){
        return sharedPreferences.getString(THURSDAY_DISARM, "");
    }

    public String getFridayArm (){
        return sharedPreferences.getString(FRIDAY_ARM, "");
    }

    public String getFridayDisarm (){
        return sharedPreferences.getString(FRIDAY_DISARM, "");
    }

    public String getSaturdayArm (){
        return sharedPreferences.getString(SATURDAY_ARM, "");
    }

    public String getSaturdayDisarm (){
        return sharedPreferences.getString(SATURDAY_DISARM, "");
    }

    public String getSundayArm (){
        return sharedPreferences.getString(SUNDAY_ARM, "");
    }

    public String getSundayDisarm (){
        return sharedPreferences.getString(SUNDAY_DISARM, "");
    }

}
