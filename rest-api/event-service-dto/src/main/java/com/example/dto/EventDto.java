package com.example.dto;

import com.example.dto.enums.EventType;

import java.util.Date;
import java.util.Objects;

public class EventDto {
    private Long id;

    public EventDto() {
    }

    public EventDto(Long id, String title, int place, String speaker, EventType eventType, Date dateTime) {
        this.id = id;
        this.title = title;
        this.place = place;
        this.speaker = speaker;
        this.eventType = eventType;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", place=" + place +
                ", speaker='" + speaker + '\'' +
                ", eventType=" + eventType +
                ", dateTime=" + dateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDto eventDto = (EventDto) o;
        return place == eventDto.place && Objects.equals(id, eventDto.id) && Objects.equals(title, eventDto.title) && Objects.equals(speaker, eventDto.speaker) && eventType == eventDto.eventType && Objects.equals(dateTime, eventDto.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, place, speaker, eventType, dateTime);
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

    private String title;
    private int place;
    private String speaker;
    private EventType eventType;
    private Date dateTime;
}
