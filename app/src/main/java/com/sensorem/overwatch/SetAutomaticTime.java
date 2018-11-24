package com.sensorem.overwatch;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.Locale;

import android.support.v4.app.FragmentManager;

public class SetAutomaticTime extends AppCompatActivity{

    private static EditText armMonday;
    private static EditText disarmMonday;

    private static EditText armTuesday;
    private static EditText disarmTuesday;

    private static EditText armWednesday;
    private static EditText disarmWednesday;

    private static EditText armThursday;
    private static EditText disarmThursday;

    private static EditText armFriday;
    private static EditText disarmFriday;

    private static EditText armSaturday;
    private static EditText disarmSaturday;

    private static EditText armSunday;
    private static EditText disarmSunday;

    private Button deleteMondayButton;
    private Button deleteTuesdayButton;
    private Button deleteWedButton;
    private Button deleteThurButton;
    private Button deleteFriButton;
    private Button deleteSatButton;
    private Button deleteSunButton;

    protected static SetTimeSharedPreferences setTimeSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_automatic_time);

        setTimeSharedPreferences = new SetTimeSharedPreferences(SetAutomaticTime.this);
        setupUI();
        deleteTime();
    }

    @Override
    protected void onStart(){
        super.onStart();

        armMonday.setText(setTimeSharedPreferences.getMondayArm());
        disarmMonday.setText(setTimeSharedPreferences.getMondayDisarm());

        armTuesday.setText(setTimeSharedPreferences.getTuesdayArm());
        disarmTuesday.setText(setTimeSharedPreferences.getTuesdayDisarm());

        armWednesday.setText(setTimeSharedPreferences.getWednesdayArm());
        disarmWednesday.setText(setTimeSharedPreferences.getWednesdayDisarm());

        armThursday.setText(setTimeSharedPreferences.getThursdayArm());
        disarmThursday.setText(setTimeSharedPreferences.getThursdayDisarm());

        armFriday.setText(setTimeSharedPreferences.getFridayArm());
        disarmFriday.setText(setTimeSharedPreferences.getFridayDisarm());

        armSaturday.setText(setTimeSharedPreferences.getSaturdayArm());
        disarmSaturday.setText(setTimeSharedPreferences.getSaturdayDisarm());

        armSunday.setText(setTimeSharedPreferences.getSundayArm());
        disarmSunday.setText(setTimeSharedPreferences.getSundayDisarm());

        deleteTime();
    }


    public void deleteTime(){
        deleteMondayButton = findViewById(R.id.deleteMondayButton);
        deleteMondayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeSharedPreferences.setMondayArm("");
                setTimeSharedPreferences.setMondayDisarm("");

                armMonday.setText(setTimeSharedPreferences.getMondayArm());
                disarmMonday.setText(setTimeSharedPreferences.getMondayDisarm());
            }
        });

        deleteTuesdayButton = findViewById(R.id.deleteTuesdayButton);
        deleteTuesdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeSharedPreferences.setTuesdayArm("");
                setTimeSharedPreferences.setTuesdayDisarm("");

                armTuesday.setText(setTimeSharedPreferences.getTuesdayArm());
                disarmTuesday.setText(setTimeSharedPreferences.getTuesdayDisarm());
            }
        });

        deleteWedButton = findViewById(R.id.deleteWedButton);
        deleteWedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeSharedPreferences.setWednesdayArm("");
                setTimeSharedPreferences.setWednesdayDisarm("");

                armWednesday.setText(setTimeSharedPreferences.getWednesdayArm());
                disarmWednesday.setText(setTimeSharedPreferences.getWednesdayDisarm());
            }
        });

        deleteThurButton = findViewById(R.id.deleteThurButton);
        deleteThurButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeSharedPreferences.setThursdayArm("");
                setTimeSharedPreferences.setThursdayDisarm("");

                armThursday.setText(setTimeSharedPreferences.getThursdayArm());
                disarmThursday.setText(setTimeSharedPreferences.getThursdayDisarm());
            }
        });

        deleteFriButton = findViewById(R.id.deleteFriButton);
        deleteFriButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeSharedPreferences.setFridayArm("");
                setTimeSharedPreferences.setFridayDisarm("");

                armFriday.setText(setTimeSharedPreferences.getFridayArm());
                disarmFriday.setText(setTimeSharedPreferences.getFridayDisarm());
            }
        });

        deleteSatButton = findViewById(R.id.deleteSatButton);
        deleteSatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeSharedPreferences.setSaturdayArm("");
                setTimeSharedPreferences.setSaturdayDisarm("");

                armSaturday.setText(setTimeSharedPreferences.getSaturdayArm());
                disarmSaturday.setText(setTimeSharedPreferences.getSaturdayDisarm());
            }
        });

        deleteSunButton = findViewById(R.id.deleteSunButton);
        deleteSunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeSharedPreferences.setSundayArm("");
                setTimeSharedPreferences.setSundayDisarm("");

                armSunday.setText(setTimeSharedPreferences.getSundayArm());
                disarmSunday.setText(setTimeSharedPreferences.getSundayDisarm());
            }
        });
    }

    public void setupUI(){

        armMonday = findViewById(R.id.armMonday);
        armMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new TimePickerFragmentArmMonday();
                dialog.show(getSupportFragmentManager(), "timePicker");
            }
        });

        disarmMonday = findViewById(R.id.disarmMonday);
        disarmMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new TimePickerFragmentDisarmMonday();
                dialog.show(getSupportFragmentManager(), "timePicker");
            }
        });

        armTuesday= findViewById(R.id.armTuesday);
        armTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new TimePickerFragmentArmTuesday();
                dialog.show(getSupportFragmentManager(), "timePicker");
            }
        });

        disarmTuesday= findViewById(R.id.disarmTuesday);
        disarmTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new TimePickerFragmentDisarmTuesday();
                dialog.show(getSupportFragmentManager(), "timePicker");
            }
        });

        armWednesday= findViewById(R.id.armWednesday);
        armWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new TimePickerFragmentArmWednesday();
                dialog.show(getSupportFragmentManager(), "timePicker");
            }
        });

        disarmWednesday= findViewById(R.id.disarmWednesday);
        disarmWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new TimePickerFragmentDisarmWednesday();
                dialog.show(getSupportFragmentManager(), "timePicker");
            }
        });

        armThursday= findViewById(R.id.armThursday);
        armThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new TimePickerFragmentArmThursday();
                dialog.show(getSupportFragmentManager(), "timePicker");
            }
        });

        disarmThursday= findViewById(R.id.disarmThursday);
        disarmThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new TimePickerFragmentDisarmThursday();
                dialog.show(getSupportFragmentManager(), "timePicker");
            }
        });

        armFriday= findViewById(R.id.armFriday);
        armFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new TimePickerFragmentArmFriday();
                dialog.show(getSupportFragmentManager(), "timePicker");
            }
        });

        disarmFriday= findViewById(R.id.disarmFriday);
        disarmFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new TimePickerFragmentDisarmFriday();
                dialog.show(getSupportFragmentManager(), "timePicker");
            }
        });

        armSaturday= findViewById(R.id.armSaturday);
        armSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new TimePickerFragmentArmSaturday();
                dialog.show(getSupportFragmentManager(), "timePicker");
            }
        });

        disarmSaturday= findViewById(R.id.disarmSaturday);
        disarmSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new TimePickerFragmentDisarmSaturday();
                dialog.show(getSupportFragmentManager(), "timePicker");
            }
        });

        armSunday= findViewById(R.id.armSunday);
        armSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new TimePickerFragmentArmSunday();
                dialog.show(getSupportFragmentManager(), "timePicker");
            }
        });

        disarmSunday= findViewById(R.id.disarmSunday);
        disarmSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new TimePickerFragmentDisarmSunday();
                dialog.show(getSupportFragmentManager(), "timePicker");
            }
        });

    }

    /*
     *
     * COULD NOT FIND ANOTHER WAY BECAUSE OF TIME CONSTRAINT
     * HAD TO MAKE MULTIPLE CLASSES
     * */


    //TIMEPICKER HELPER CLASS FOE ARM MONDAY
    public static class TimePickerFragmentArmMonday extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            //Use the current time as the default value
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String theHour, theMinute;
            theHour = Integer.toString(hourOfDay);
            theMinute = Integer.toString(minute);

            setTimeSharedPreferences.setMondayArm(theHour + ":" + theMinute);
            armMonday.setText(setTimeSharedPreferences.getMondayArm());
        }

    }

    //TIMEPICKER HELPER CLASS FOE DISARM MONDAY
    public static class TimePickerFragmentDisarmMonday extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            //Use the current time as the default value
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String theHour, theMinute;
            theHour = Integer.toString(hourOfDay);
            theMinute = Integer.toString(minute);

            setTimeSharedPreferences.setMondayDisarm(theHour + ":" + theMinute);
            disarmMonday.setText(setTimeSharedPreferences.getMondayDisarm());
        }
    }

    //TIMEPICKER HELPER CLASS FOE ARM TUESDAY
    public static class TimePickerFragmentArmTuesday extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            //Use the current time as the default value
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String theHour, theMinute;
            theHour = Integer.toString(hourOfDay);
            theMinute = Integer.toString(minute);

            setTimeSharedPreferences.setTuesdayArm(theHour + ":" + theMinute);
            armTuesday.setText(setTimeSharedPreferences.getTuesdayArm());
        }
    }

    //TIMEPICKER HELPER CLASS FOE DISARM TUESDAY
    public static class TimePickerFragmentDisarmTuesday extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            //Use the current time as the default value
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String theHour, theMinute;
            theHour = Integer.toString(hourOfDay);
            theMinute = Integer.toString(minute);

            setTimeSharedPreferences.setTuesdayDisarm(theHour + ":" + theMinute);
            disarmTuesday.setText(setTimeSharedPreferences.getTuesdayDisarm());
        }
    }

    //TIMEPICKER HELPER CLASS FOE ARM WEDNESDAY
    public static class TimePickerFragmentArmWednesday extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            //Use the current time as the default value
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String theHour, theMinute;
            theHour = Integer.toString(hourOfDay);
            theMinute = Integer.toString(minute);

            setTimeSharedPreferences.setWednesdayArm(theHour + ":" + theMinute);
            armWednesday.setText(setTimeSharedPreferences.getWednesdayArm());
        }
    }

    //TIMEPICKER HELPER CLASS FOE DISARM WEDNESDAY
    public static class TimePickerFragmentDisarmWednesday extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            //Use the current time as the default value
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String theHour, theMinute;
            theHour = Integer.toString(hourOfDay);
            theMinute = Integer.toString(minute);

            setTimeSharedPreferences.setWednesdayDisarm(theHour + ":" + theMinute);
            disarmWednesday.setText(setTimeSharedPreferences.getWednesdayDisarm());
        }
    }

    //TIMEPICKER HELPER CLASS FOE ARM THURSDAY
    public static class TimePickerFragmentArmThursday extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            //Use the current time as the default value
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String theHour, theMinute;
            theHour = Integer.toString(hourOfDay);
            theMinute = Integer.toString(minute);

            setTimeSharedPreferences.setThursdayArm(theHour + ":" + theMinute);
            armThursday.setText(setTimeSharedPreferences.getThursdayArm());
        }
    }

    //TIMEPICKER HELPER CLASS FOE DISARM THURSDAY
    public static class TimePickerFragmentDisarmThursday extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            //Use the current time as the default value
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String theHour, theMinute;
            theHour = Integer.toString(hourOfDay);
            theMinute = Integer.toString(minute);

            setTimeSharedPreferences.setThursdayDisarm(theHour + ":" + theMinute);
            disarmThursday.setText(setTimeSharedPreferences.getThursdayDisarm());
        }
    }

    //TIMEPICKER HELPER CLASS FOE ARM FRIDAY
    public static class TimePickerFragmentArmFriday extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            //Use the current time as the default value
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String theHour, theMinute;
            theHour = Integer.toString(hourOfDay);
            theMinute = Integer.toString(minute);

            setTimeSharedPreferences.setFridayArm(theHour + ":" + theMinute);
            armFriday.setText(setTimeSharedPreferences.getFridayArm());
        }
    }

    //TIMEPICKER HELPER CLASS FOE DISARM FRIDAY
    public static class TimePickerFragmentDisarmFriday extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            //Use the current time as the default value
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String theHour, theMinute;
            theHour = Integer.toString(hourOfDay);
            theMinute = Integer.toString(minute);

            setTimeSharedPreferences.setFridayDisarm(theHour + ":" + theMinute);
            disarmFriday.setText(setTimeSharedPreferences.getFridayDisarm());
        }
    }

    //TIMEPICKER HELPER CLASS FOE ARM SATURDAY
    public static class TimePickerFragmentArmSaturday extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            //Use the current time as the default value
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String theHour, theMinute;
            theHour = Integer.toString(hourOfDay);
            theMinute = Integer.toString(minute);

            setTimeSharedPreferences.setSaturdayArm(theHour + ":" + theMinute);
            armSaturday.setText(setTimeSharedPreferences.getSaturdayArm());
        }
    }

    //TIMEPICKER HELPER CLASS FOE DISARM SATURDAY
    public static class TimePickerFragmentDisarmSaturday extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            //Use the current time as the default value
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String theHour, theMinute;
            theHour = Integer.toString(hourOfDay);
            theMinute = Integer.toString(minute);

            setTimeSharedPreferences.setSaturdayDisarm(theHour + ":" + theMinute);
            disarmSaturday.setText(setTimeSharedPreferences.getSaturdayDisarm());
        }
    }

    //TIMEPICKER HELPER CLASS FOE ARM SUNDAY
    public static class TimePickerFragmentArmSunday extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            //Use the current time as the default value
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String theHour, theMinute;
            theHour = Integer.toString(hourOfDay);
            theMinute = Integer.toString(minute);

            setTimeSharedPreferences.setSundayArm(theHour + ":" + theMinute);
            armSunday.setText(setTimeSharedPreferences.getSundayArm());
        }
    }

    //TIMEPICKER HELPER CLASS FOE DISARM SUNDAY
    public static class TimePickerFragmentDisarmSunday extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            //Use the current time as the default value
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String theHour, theMinute;
            theHour = Integer.toString(hourOfDay);
            theMinute = Integer.toString(minute);

            setTimeSharedPreferences.setSundayDisarm(theHour + ":" + theMinute);
            disarmSunday.setText(setTimeSharedPreferences.getSundayDisarm());
        }
    }

}
