package com.cpd.springcore.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.*;

@XStreamAlias("ticket")
@Entity
@Table(name = "ticket")
public class Ticket {
    public Ticket() {

    }

    @XStreamAlias("category")
    public enum Category {
        BAR, PREMIUM;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "event_id")
    private long eventId;
    @Column(name = "user_id")
    private long userId;
    private int place;

    private Category category;

    public long getId() {
        return id;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
