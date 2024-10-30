package com.alex.person;

import com.alex.SortingOrder;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
    @GetMapping()
    public List<Person> getPersons(@RequestParam(
            value = "sort",
            required = false,
            defaultValue = "DESC") SortingOrder sort,
                                   @RequestParam(
                                           value = "limit",
                                           required = false,
                                           defaultValue = "10") Integer limit) {

        if (sort == SortingOrder.ASC) {
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

    @PutMapping("{id}")
    public void updatePerson(@PathVariable("id") Integer id, @RequestBody PersonUpdateRequest request) {
        // find person by id
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
