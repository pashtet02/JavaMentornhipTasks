package com.example.springboot;

import com.example.springboot.entity.User;
import com.example.springboot.repos.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepo userRepo) {
        return (args) -> {
            User user = User.builder().age(15).email("test@gmail.com").password("pass").username("test").build();
            User createdUser = userRepo.save(user);
            LOGGER.info("Application start");
            if (args.length > 0) {
                System.out.println("\n\n\n" + "Hello " + args[0] + "!\n\n\n");
            } else {
                System.out.println("\n\n" + "Hello world!" + "\n\n");
            }
            LOGGER.info("\nUser {} was created\n", createdUser);
            LOGGER.info("\nUser {} was found in DB\n", userRepo.findById(createdUser.getId()));

            createdUser.setUsername("NEW USERNAME");
            LOGGER.info("\nUser {} was updated in DB\n", userRepo.save(createdUser));

            userRepo.delete(createdUser);
            LOGGER.info("\nUser was deleted from DB, total number of users now: {}\n", userRepo.count());
            LOGGER.info("Application shut down");
        };
    }
}
