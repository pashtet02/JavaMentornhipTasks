package com.example.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
            LOGGER.info("Application start");
            if (args.length > 0) {
                System.out.println("\n\n\n" + "Hello " + args[0] + "!\n\n\n");
            } else {
                System.out.println("\n\n" + "Hello world!" + "\n\n");
            }
            LOGGER.info("Application shut down");
    }
}
