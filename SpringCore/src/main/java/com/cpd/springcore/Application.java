package com.cpd.springcore;

import com.cpd.springcore.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
