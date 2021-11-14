package com.cpd.springcore.config;

import com.cpd.springcore.model.Event;
import com.cpd.springcore.model.Ticket;
import com.cpd.springcore.model.User;
import com.cpd.springcore.xml.TicketList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("com.cpd.springcore")
public class AppConfig {

    @Bean
    public XStream xStreamMarshallerBean() {
        XStream xstream = new XStream(new StaxDriver());
        xstream.allowTypesByWildcard(new String[]{"com.cpd.springcore.**"});
        xstream.alias("ticket", Ticket.class);
        xstream.alias("user", User.class);
        xstream.alias("event", Event.class);
        xstream.alias("category", Ticket.Category.class);
        xstream.alias("tickets", TicketList.class);
        xstream.addImplicitCollection(TicketList.class, "tickets");
        return xstream;
    }
}
