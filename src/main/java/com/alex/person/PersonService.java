package com.alex.person;

import com.alex.SortingOrder;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static com.alex.Application.idCounter;


public class PersonService {
    public List<Person> getPeople(SortingOrder sort) {

        if (sort == SortingOrder.ASC) {
            return people.stream()
                    .sorted(Comparator.comparing(Person::id))
                    .collect(Collectors.toList());
        }
        return people.stream()
                .sorted(Comparator.comparing(Person::id).reversed())
                .collect(Collectors.toList());
    }

    public void addPerson(Person person) {
        people.add(new Person(idCounter.incrementAndGet(), person.name, person.age, person.gender));
    }


    public Optional<Person> getPersonById(Integer id) {
        return people.stream()
                .filter(person -> person.id.equals(id))
                .findFirst();
    }


    public void deletePersonById(Integer id) {
        people.removeIf(person -> person.id.equals(id));
    }


    public void updatePerson(Integer id, PersonUpdateRequest request) {

        people.stream()
                .filter(p -> p.id.equals(id))
                .findFirst()
                .ifPresent(p -> {
                    var index = people.indexOf(p);

                    if (request.name != null && !request.name.isEmpty() && !request.name.equals(p.name)) {
                        Person person = new Person(
                                p.id,
                                request.name,
                                p.age(),
                                p.gender()
                        );

                        people.set(index, person);
                    }

                    if (request.age != null && !request.age.equals(p.age)) {
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
}
