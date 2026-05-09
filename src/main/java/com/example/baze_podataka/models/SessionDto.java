package com.example.baze_podataka.models;

import java.sql.Time;
import java.util.Date;

public class SessionDto {
    private int sessionId;
    private Date date;
    private Time startTime;
    private Time endTime;

    public SessionDto(int sessionId, Date date, Time startTime, Time endTime) {
        this.sessionId = sessionId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
