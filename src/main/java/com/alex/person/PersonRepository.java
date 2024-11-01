package com.alex.person;



import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Repository
public class PersonRepository {

    private final AtomicInteger idCounter =
            new AtomicInteger(0);

    private final List<Person> people = new ArrayList<>();

   {
        people.add(new Person(idCounter.incrementAndGet(), "John", 20, Gender.MALE, "12345"));
        people.add(new Person(idCounter.incrementAndGet(), "Jane", 22, Gender.FEMALE, "hello12345"));
        people.add(new Person(idCounter.incrementAndGet(), "Bob", 24, Gender.MALE, "yoyo12345"));
        people.add(new Person(idCounter.incrementAndGet(), "Alice", 26, Gender.FEMALE, "qwerty"));
    }

    public AtomicInteger getIdCounter() {
        return idCounter;
    }

    public List<Person> getPeople() {
        return people;
    }
}
