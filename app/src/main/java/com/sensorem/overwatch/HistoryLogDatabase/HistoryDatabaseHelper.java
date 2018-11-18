package com.sensorem.overwatch.HistoryLogDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class HistoryDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = Config.DATABASE_NAME;
    private Context context = null;

    // Constructor
    public HistoryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tables SQL execution
        String CREATE_EVENT_TABLE = "CREATE TABLE " + Config.TABLE_EVENT + "("
                + Config.COLUMN_EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_EVENT_NAME + " TEXT NOT NULL, "
                + Config.COLUMN_EVENT_DATE_TIME + " TEXT NOT NULL "
                + ")";

        Log.d(TAG,"Table create SQL: " + CREATE_EVENT_TABLE);

        db.execSQL(CREATE_EVENT_TABLE);
        Log.d(TAG,"DB created!");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_EVENT);

        // Create tables again
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    public long insertEvent(Events event){

        long id = -1;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateTime = simpleDateFormat.format(event.getSavedDateTime().getTime());

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_EVENT_NAME, event.getEventName());
        contentValues.put(Config.COLUMN_EVENT_DATE_TIME, dateTime);


        try {
            id = sqLiteDatabase.insertOrThrow(Config.TABLE_EVENT, null, contentValues);
        } catch (SQLiteException e){
            Log.d(TAG,"Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }
        return id;
    }

    public List<Events> getAllEvents(){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = null;

        try {

            cursor = sqLiteDatabase.query(Config.TABLE_EVENT, null, null, null, null, null, null, null);

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<Events> eventList = new ArrayList<>();
                    do {

                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_EVENT_ID));
                        String eventName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_EVENT_NAME));
                        String savedDateTime = cursor.getString(cursor.getColumnIndex(Config.COLUMN_EVENT_DATE_TIME));

                        // convert string back to calendar object
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(sdf.parse(savedDateTime));

                        // insert in the list of events
                        eventList.add(new Events(id, eventName, calendar));

                    } while (cursor.moveToNext());

                    return eventList;
                }
        } catch (Exception e){
            Log.d(TAG,"Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null) {
                cursor.close();
            }
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public void deleteEventIf24hour(Calendar currentDateTime){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = null;

        // subtract 1 day to compare with date and time within table
        // If we do currentDateTime -1 day and that the dateTime event is still before currentDateTime -1, it means it has been there for more than 24 hours.
        currentDateTime.add(Calendar.DATE, -1);

        // we will scheme through every event and check if they have been there for more than 24 hours
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_EVENT, null, null, null, null, null, null, null);

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_EVENT_ID));
                        String date_time = cursor.getString(cursor.getColumnIndex(Config.COLUMN_EVENT_DATE_TIME));

                        // convert string back to calendar object
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
                        Calendar savedDateTime = Calendar.getInstance();
                        savedDateTime.setTime(sdf.parse(date_time));

                        if(savedDateTime.before(currentDateTime)){
                            // if this is true, then we will delete the event from the table
                            deleteEventById(String.valueOf(id));
                        }
                        else{
                            // do nothing, still within 24h span
                            return;
                        }

                    } while (cursor.moveToNext());
                }
        } catch (Exception e){
            Log.d(TAG,"Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null) {
                cursor.close();
            }
            sqLiteDatabase.close();
        }
    }

    public long deleteEventById(String id) {
        long deletedRowCount = -1;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        try {
            deletedRowCount = sqLiteDatabase.delete(Config.TABLE_EVENT,
                    Config.COLUMN_EVENT_ID + " = ? ",
                    new String[]{ id });
        } catch (SQLiteException e){
            Log.d(TAG,"Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deletedRowCount;
    }
}
