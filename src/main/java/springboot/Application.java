package springboot;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/people")
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

    public enum Gender {MALE, FEMALE}

    public enum Sort {ASC, DESC}

    public record Person(Integer id, String name, Integer age, Gender gender) {
    }

    private static AtomicInteger idCounter = new AtomicInteger(0);

    public static List<Person> people = new ArrayList<>();

    static {
        people.add(new Person(idCounter.incrementAndGet(), "John", 20, Gender.MALE));
        people.add(new Person(idCounter.incrementAndGet(), "Jane", 22, Gender.FEMALE));
        people.add(new Person(idCounter.incrementAndGet(), "Bob", 24, Gender.MALE));
        people.add(new Person(idCounter.incrementAndGet(), "Alice", 26, Gender.FEMALE));
    }

    @GetMapping()
    public List<Person> getPersons(@RequestParam(
            value = "sort",
            required = false,
            defaultValue = "DESC") Sort sort,
                                   @RequestParam(
                                           value = "limit",
                                           required = false,
                                           defaultValue = "10") Integer limit) {

        if (sort == Sort.ASC) {
            return people.stream()
                    .sorted(Comparator.comparing(Person::id))
                    .collect(Collectors.toList());
        }
        return people.stream()
                .sorted(Comparator.comparing(Person::id).reversed())
                .collect(Collectors.toList());
    }

    @PostMapping()
    public void addPerson(@RequestBody Person person) {
        people.add(new Person(idCounter.incrementAndGet(), person.name, person.age, person.gender));
    }

    @GetMapping("{id}")
    public Optional<Person> getPersonById(@PathVariable("id") Integer id) {
        return people.stream()
                .filter(person -> person.id.equals(id))
                .findFirst();
    }

    @DeleteMapping("{id}")
    public void deletePersonById(@PathVariable("id") Integer id) {
        people.removeIf(person -> person.id.equals(id));
    }

    public record PersonUpdateRequest(
            String name,
            Integer age
    ) {

    }

    @PutMapping("{id}")
    public void updatePerson(@PathVariable("id") Integer id, @RequestBody PersonUpdateRequest request) {
        // find person by id
        people.stream()
                .filter(p -> p.id.equals(id))
                .findFirst()
                .ifPresent(p -> {
                    var index = people.indexOf(p);

                    if(request.name != null && !request.name.isEmpty() && !request.name.equals(p.name) ) {
                        Person person = new Person(
                                p.id,
                                request.name,
                                p.age(),
                                p.gender()
                        );

                        people.set(index, person);
                    }

                    if(request.age != null && !request.age.equals(p.age) ) {
                        Person person = new Person(
                                p.id,
                                p.name,
                                request.age,
                                p.gender()
                        );

                        people.set(index, person);
                    }
                });


    }



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
