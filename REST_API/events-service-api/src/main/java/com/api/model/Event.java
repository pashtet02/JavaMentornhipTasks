package com.api.model;

import org.springframework.hateoas.RepresentationModel;

public abstract class Event extends RepresentationModel<Event> {
    public abstract Long getId();
}