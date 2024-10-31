package com.alex.person;

import com.alex.SortingOrder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static com.alex.Application.idCounter;


@Service
public class PersonService {

    public static List<Person> people = new ArrayList<>();

    static {
        people.add(new Person(idCounter.incrementAndGet(), "John", 20, Gender.MALE, "12345"));
        people.add(new Person(idCounter.incrementAndGet(), "Jane", 22, Gender.FEMALE, "hello12345"));
        people.add(new Person(idCounter.incrementAndGet(), "Bob", 24, Gender.MALE, "yoyo12345"));
        people.add(new Person(idCounter.incrementAndGet(), "Alice", 26, Gender.FEMALE, "qwerty"));
    }

    public List<Person> getPeople(SortingOrder sort) {

        if (sort == SortingOrder.ASC) {
            return people.stream()
                    .sorted(Comparator.comparing(Person::getId))
                    .collect(Collectors.toList());
        }
        return people.stream()
                .sorted(Comparator.comparing(Person::getId).reversed())
                .collect(Collectors.toList());
    }

    public void addPerson(Person person) {
        people.add(new Person(idCounter.incrementAndGet(), person.getName(), person.getAge(), person.getGender(), person.getPassword()));
    }


    public Optional<Person> getPersonById(Integer id) {
        return people.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }


    public void deletePersonById(Integer id) {
        people.removeIf(person -> person.getId().equals(id));
    }


    public void updatePerson(Integer id, PersonUpdateRequest request) {

        people.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .ifPresent(p -> {
                    var index = people.indexOf(p);

                    if (request.name() != null && !request.name().isEmpty() && !request.name().equals(p.getName())) {
                        Person person = new Person(
                                p.getId(),
                                request.name(),
                                p.getAge(),
                                p.getGender(),
                                p.getPassword()
                        );

                        people.set(index, person);
                    }

                    if (request.age() != null && !request.age().equals(p.getAge())) {
                        Person person = new Person(
                                p.getId(),
                                p.getName(),
                                request.age(),
                                p.getGender(),
                                p.getPassword()
                        );

                        people.set(index, person);
                    }
                });


    }
}
