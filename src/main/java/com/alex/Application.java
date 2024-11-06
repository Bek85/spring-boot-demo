package com.alex;

import com.alex.person.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import com.alex.config.StripeConfig;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Application {

  @Value("${stripe.api-key}")
  private String stripeApiKey;

  @Value("${stripe.url}")
  private String stripeUrl;

  @Value("${spring.application.name}")
  private String applicationName;

  @Bean
  CommandLineRunner commandLineRunner(Environment environment, StripeConfig stripeConfig) {
    System.out.println(stripeApiKey);
    System.out.println(stripeUrl);
    System.out.println(applicationName);
    System.out.println(environment.getProperty("stripe.api-key"));
    System.out.println(stripeConfig);
    return args -> {
    };
  }

  public static void main(String[] args) {

    SpringApplication.run(Application.class, args);

    // ConfigurableApplicationContext context =
    // SpringApplication.run(Application.class, args);

    // String[] beanDefinitionNames = context.getBeanDefinitionNames();

    // for (String beanDefinitionName : beanDefinitionNames) {
    // // System.out.println(beanDefinitionName);
    // }

    // System.out.println(beanDefinitionNames.length);
    // System.out.println("Hello World!!!");
  }

  // add cron expression to run the task every 1 minute

  // @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS // run every 1 minute
  // )
  // @Async
  // public void sendEmails() throws InterruptedException {
  // System.out.println("Start sending emails");
  // Thread.sleep(5000);
  // System.out.println("End sending emails");
  // }

  // @Scheduled(cron = "*/5 * * * * *")
  // @Async
  // public void generateSalesReport() throws InterruptedException {
  // System.out.println("Start generating sales report");
  // Thread.sleep(5000);
  // System.out.println("End generating sales report");
  // }

  // @Bean() // By default, Bean objects are Singletons
  // @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // Can be changed from
  // singleton to other
  // @SessionScope
  // @ApplicationScope
  // public String redBean() {
  // return "Manchester United";
  // }

  // @Bean
  // public String blueBean() {
  // return "Chelsea";
  // }

  // @Bean
  // CommandLineRunner commandLineRunner(String redBean, String blueBean,
  // UserService userService) {
  // return args -> {
  // System.out.println("Hello from CommandLineRunner");
  // System.out.println(redBean);
  // System.out.println(blueBean);
  // System.out.println(userService.getUsers());
  // };
  // }

  // @Bean
  // CommandLineRunner commandLineRunner2(String redBean, String blueBean,
  // UserService userService) {
  // return args -> {
  // System.out.println("Hello from CommandLineRunner");
  // System.out.println(redBean);
  // System.out.println(blueBean);
  // System.out.println(userService.getUserById(2));
  // System.out.println(userService.getUserById(3));
  // };
  // }

  // @Bean
  // CommandLineRunner commandLineRunner3(ObjectMapper objectMapper) throws
  // JsonProcessingException {
  // String personString = "{\"id\":1,\"name\":\"John Doe\",\"age\":2}";
  // Person person = objectMapper.readValue(personString, Person.class);
  // System.out.println(person);
  // System.out.println(objectMapper.writeValueAsString(person));
  // return args -> {

  // };
  // }

  // public record User(int id, String name) {
  // }

  // @Service
  // public class UserService {

  // public UserService() {
  // System.out.println("User Service Constructor");
  // }

  // public List<User> getUsers() {
  // return List.of(
  // new User(1, "John Doe"),
  // new User(2, "Alex Smith")
  // );
  // }

  // public Optional<User> getUserById(int id) {
  // return getUsers().stream()
  // .filter(u -> u.id == id)
  // .findFirst();

  // }

  // @PostConstruct
  // public void init() {
  // System.out.println("Fill redis cache");
  // }

  // @PreDestroy
  // public void destroy() {
  // System.out.println("Clear redis cache");
  // }

  // }

}
