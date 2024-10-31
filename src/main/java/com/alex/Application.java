package com.alex;

import com.alex.person.Gender;
import com.alex.person.Person;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;



@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
        }

        System.out.println(beanDefinitionNames.length);
        System.out.println("Hello World!!!");
    }

    public static AtomicInteger idCounter = new AtomicInteger(0);

    @Bean() // By default, Bean objects are Singletons
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // Can be changed from singleton to other
//    @SessionScope
//    @ApplicationScope
    public String redBean() {
        return "Manchester United";
    }

    @Bean
    public String blueBean() {
        return "Chelsea";
    }

    @Bean
    CommandLineRunner commandLineRunner(String redBean, String blueBean, UserService userService) {
        return args -> {
            System.out.println("Hello from CommandLineRunner");
            System.out.println(redBean);
            System.out.println(blueBean);
            System.out.println(userService.getUsers());
        };
    }

    @Bean
    CommandLineRunner commandLineRunner2(String redBean, String blueBean, UserService userService) {
        return args -> {
            System.out.println("Hello from CommandLineRunner");
            System.out.println(redBean);
            System.out.println(blueBean);
            System.out.println(userService.getUserById(2));
            System.out.println(userService.getUserById(3));
        };
    }

    public record User(int id, String name) {
    }

    @Service
    public class UserService {

        public UserService() {
            System.out.println("User Service Constructor");
        }

        public List<User> getUsers() {
            return List.of(
                    new User(1, "John Doe"),
                    new User(2, "Alex Smith")
            );
        }

        public Optional<User> getUserById(int id) {
            return getUsers().stream()
                    .filter(u -> u.id == id)
                    .findFirst();

        }

        @PostConstruct
        public void init() {
            System.out.println("Fill redis cache");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("Clear redis cache");
        }


    }

}
