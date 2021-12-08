package com.impl.model;

import com.api.model.Event;
import com.example.dto.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Entity
@ToString
@RequiredArgsConstructor
@Table(name = "event")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventImpl extends Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private int place;
    private String speaker;
    private EventType eventType;
    private Date dateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EventImpl event = (EventImpl) o;

        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return 480561615;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getPlace() {
        return place;
    }

    @Override
    public void setPlace(int place) {
        this.place = place;
    }

    @Override
    public String getSpeaker() {
        return speaker;
    }

    @Override
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }

    @Override
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public Date getDateTime() {
        return dateTime;
    }

    @Override
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
