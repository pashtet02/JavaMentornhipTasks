package com.example.springboot;

import com.example.springboot.domain.Role;
import com.example.springboot.domain.User;
import com.example.springboot.repos.UserRepo;
import com.example.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Profile("!test")
    public CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));

            userService.saveUser(User.builder().id(null).username("Pavlo").age(19).password("pass").email("pashtetGusia@gmail.com").build());
            userService.saveUser(User.builder().id(null).username("Josh").age(20).password("pass").email("test@gmail.com").build());

            userService.addRoleToUser("Pavlo", "ROLE_ADMIN");
            userService.addRoleToUser("Josh", "ROLE_USER");
        };
    }

    @Bean
    @Profile("task1")
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
