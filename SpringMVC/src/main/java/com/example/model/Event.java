package com.example.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    public Event(String name, String details, double price, String location) {
        this.name = name;
        this.details = details;
        this.price = price;
        this.location = location;
    }

    public Event() {
    }

    private String name;
    private String details;
    private String location;
    private double price;

    @OneToMany
    @ToString.Exclude
    private List<Ticket> ticketList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Event event = (Event) o;

        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return 1491041522;
    }
}
