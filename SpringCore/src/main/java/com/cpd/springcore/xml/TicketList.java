package com.cpd.springcore.xml;

import com.cpd.springcore.model.Ticket;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("tickets")
@Getter
public class TicketList {

    @XStreamImplicit(itemFieldName = "ticket")
    private final List<Ticket> tickets;

    public TicketList() {
        this.tickets = new ArrayList<>();
    }

    public boolean add(Ticket ticket){
        return tickets.add(ticket);
    }
}
