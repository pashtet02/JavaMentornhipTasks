package com.cpd.springcore;

import com.cpd.springcore.config.AppConfig;
import com.cpd.springcore.facade.BookingFacade;
import com.cpd.springcore.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BookingFacade facade = context.getBean(BookingFacade.class);

        User userById = facade.getUserById(1);
    }
}
