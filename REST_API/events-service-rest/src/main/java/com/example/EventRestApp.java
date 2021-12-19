package com.example;

import com.example.config.SpringFoxConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"com.impl.*", "com.example.*", "com.example.config"})
@EntityScan(basePackages = "com.impl.model")
@EnableJpaRepositories(basePackages = "com.impl.repos")
@EnableSwagger2
public class EventRestApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(EventRestApp.class, args);
        SpringFoxConfig bean = run.getBean(SpringFoxConfig.class);
        System.out.println(bean);
    }
}


