package com.alex.person;

import com.alex.SortingOrder;
import com.alex.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

  private final PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public List<Person> getPeople(SortingOrder sort) {

    if (sort == SortingOrder.ASC) {
      return personRepository.getPeople().stream()
          .sorted(Comparator.comparing(Person::getId))
          .collect(Collectors.toList());
    }
    return personRepository.getPeople().stream()
        .sorted(Comparator.comparing(Person::getId).reversed())
        .collect(Collectors.toList());
  }

  public Person addPerson(NewPersonRequest person) {
    Person newPerson = new Person(
        personRepository.getIdCounter().incrementAndGet(),
        person.name(),
        person.age(),
        person.gender(),
        person.email(),
        person.password());
    personRepository.getPeople().add(newPerson);
    return newPerson;
  }

  public Person getPersonById(Integer id) {
    return personRepository.getPeople().stream()
        .filter(person -> person.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new ResourceNotFoundException("Person with id " + id + " not found"));
  }

  public void deletePersonById(Integer id) {
    personRepository.getPeople().removeIf(person -> person.getId().equals(id));
  }

  public void updatePerson(Integer id, PersonUpdateRequest request) {

    personRepository.getPeople().stream()
        .filter(p -> p.getId().equals(id))
        .findFirst()
        .ifPresent(p -> {
          var index = personRepository.getPeople().indexOf(p);

          if (request.name() != null && !request.name().isEmpty() && !request.name().equals(p.getName())) {
            Person person = new Person(
                p.getId(),
                request.name(),
                p.getAge(),
                p.getGender(),
                p.getEmail(),
                p.getPassword());

            personRepository.getPeople().set(index, person);
          }

          if (request.age() != null && !request.age().equals(p.getAge())) {
            Person person = new Person(
                p.getId(),
                p.getName(),
                request.age(),
                p.getGender(),
                p.getEmail(),
                p.getPassword());

            personRepository.getPeople().set(index, person);
          }
        });

  }
}
