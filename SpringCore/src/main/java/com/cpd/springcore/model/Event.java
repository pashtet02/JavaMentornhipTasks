package com.cpd.springcore.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event")
@XStreamAlias("event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private Date date;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
