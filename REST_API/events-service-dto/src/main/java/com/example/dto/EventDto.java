package com.example.dto;

import com.example.dto.enums.EventType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;
import java.util.Objects;

@ApiModel(description = "Details about an event")
public class EventDto {
    private Long id;

    public EventDto() {
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

    public java.sql.Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(java.sql.Date dateTime) {
        this.dateTime = dateTime;
    }

    @ApiModelProperty(notes = "Event title")
    private String title;
    @ApiModelProperty(notes = "The total number of places")
    private int place;
    @ApiModelProperty(notes = "The speaker`s full name")
    private String speaker;
    @ApiModelProperty(notes = "The Event type")
    private EventType eventType;
    @ApiModelProperty(notes = "Event date")
    private Date dateTime;
}
