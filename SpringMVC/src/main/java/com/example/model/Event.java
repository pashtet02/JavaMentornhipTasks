package com.example.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, length = 254, nullable = false)
    private String name;
    private String details;
    private String location;

    private double price;

    @Temporal(value = TemporalType.DATE)
    private Date eventDate;

    @Temporal(value = TemporalType.TIME)
    private Date eventTime;



    @Temporal(value = TemporalType.TIMESTAMP)
    public Date creationDtTm;

    @OneToMany
    @ToString.Exclude
    private List<Ticket> ticketList;
}
