/*package com.example.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("tickets")
public class TicketXml {

    private Ticket ticket;

    @XStreamAlias("ticket")
    private class Ticket {
        @XStreamAlias("user")
        private int user;
        @XStreamAlias("event")
        private int event;

        public Ticket() {
        }

        public Ticket(int user, int event) {
            this.user = user;
            this.event = event;
        }

        public int getUser() {
            return user;
        }

        public void setUser(int user) {
            this.user = user;
        }

        public int getEvent() {
            return event;
        }

        public void setEvent(int event) {
            this.event = event;
        }
    }


    public TicketXml() {
    }

    public TicketXml(Ticket ticket) {
        this.ticket = ticket;
    }
}*/
