package com.api.model;

import com.example.dto.enums.EventType;

import java.sql.Date;

public class Event {
    private Long id;
    private String title;
    private int place;
    private String speaker;
    private EventType eventType;
    private Date dateTime;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", place=" + place +
                ", speaker='" + speaker + '\'' +
                ", eventType=" + eventType +
                ", dateTime=" + dateTime +
                '}';
    }

    public Event() {
    }

    public Event(Long id, String title, int place, String speaker, EventType eventType, Date dateTime) {
        this.id = id;
        this.title = title;
        this.place = place;
        this.speaker = speaker;
        this.eventType = eventType;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
