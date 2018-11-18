package com.sensorem.overwatch.HistoryLogDatabase;

import java.util.Calendar;

public class Events {

    private long id;
    private String eventName;
    private Calendar savedDateTime;

    public Events(int id, String eventName, Calendar savedDateTime){
        this.id = id;
        this.eventName = eventName;
        this.savedDateTime = savedDateTime;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getEventName(){
        return eventName;
    }

    public void setEventName(String name){
        this.eventName = name;
    }

    public Calendar getSavedDateTime() {
        return savedDateTime;
    }
}
