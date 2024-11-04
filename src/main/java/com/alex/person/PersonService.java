package com.alex.person;

import com.alex.SortingOrder;
import com.alex.exception.DuplicateResourceException;
import com.alex.exception.ResourceNotFoundException;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

  private final MockPersonRepository mockPersonRepository;
  private final PersonRepository personRepository;

  public PersonService(MockPersonRepository mockPersonRepository, PersonRepository personRepository) {
    this.mockPersonRepository = mockPersonRepository;
    this.personRepository = personRepository;
  }

  public List<Person> getPeople(SortingOrder sort) {

    return personRepository
        .findAll(Sort.by(Sort.Direction.valueOf(sort.name()), "id"));

    // if (sort == SortingOrder.ASC) {
    // return mockPersonRepository.getPeople().stream()
    // .sorted(Comparator.comparing(Person::getId))
    // .collect(Collectors.toList());
    // }
    // return mockPersonRepository.getPeople().stream()
    // .sorted(Comparator.comparing(Person::getId).reversed())
    // .collect(Collectors.toList());
  }

  public Person addPerson(NewPersonRequest person) {

    if (person.email() != null && !person.email().isEmpty()) {
      boolean existsByEmail = personRepository.existsByEmail(person.email());

      if (existsByEmail) {
        throw new DuplicateResourceException("Person with email " + person.email() + " already exists");
      }
    }

    Person personToAdd = new Person(
        person.name(),
        person.age(),
        person.gender(),
        person.email(),
        person.password());

    return personRepository.save(personToAdd);

    // Person newPerson = new Person(
    // mockPersonRepository.getIdCounter().incrementAndGet(),
    // person.name(),
    // person.age(),
    // person.gender(),
    // person.email(),
    // person.password());
    // mockPersonRepository.getPeople().add(newPerson);
    // return newPerson;
  }

  public Person getPersonById(Integer id) {
    return personRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Person with id " + id + " not found"));
    // return mockPersonRepository.getPeople().stream()
    // .filter(person -> person.getId().equals(id))
    // .findFirst()
    // .orElseThrow(() -> new ResourceNotFoundException("Person with id " + id + "
    // not found"));
  }

  public void deletePersonById(Integer id) {
    boolean existsById = personRepository.existsById(id);

    if (!existsById) {
      throw new ResourceNotFoundException("Person with id " + id + " not found");
    }
    personRepository.deleteById(id);

    // mockPersonRepository.getPeople().removeIf(person ->
    // person.getId().equals(id));
  }

  public void updatePerson(Integer id, PersonUpdateRequest request) {
    Person person = personRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Person with id " + id + " not found"));

    // Check if email is being updated and if it already exists
    if (request.email() != null &&
        !request.email().equals(person.getEmail()) &&
        personRepository.existsByEmail(request.email())) {
      throw new DuplicateResourceException(
          "Email " + request.email() + " is already taken");
    }

    // Update fields if they are present in the request
    if (request.name() != null && !request.name().isEmpty()) {
      person.setName(request.name());
    }
    if (request.age() != null) {
      person.setAge(request.age());
    }
    if (request.gender() != null) {
      person.setGender(request.gender());
    }
    if (request.email() != null && !request.email().isEmpty()) {
      person.setEmail(request.email());
    }

    personRepository.save(person);
  }
}
