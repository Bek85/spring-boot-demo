package com.alex.person;

import com.alex.SortingOrder;
import com.alex.publication.Publication;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/people")
public class PersonController {

  private final PersonService personService;
  private final Validator validator;
  private final PersonRepository personRepository;

  public PersonController(PersonService personService, Validator validator, PersonRepository personRepository) {
    this.personService = personService;
    this.validator = validator;
    this.personRepository = personRepository;
  }

  @GetMapping()
  public List<Person> getPeople(
      HttpMethod httpMethod,
      ServletRequest request,
      ServletResponse response,
      @RequestHeader("Content-Type") String contentType,
      @RequestParam(value = "sort", required = false) String sortParam,
      @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
    System.out.println(httpMethod);
    System.out.println(request.getLocalAddr());
    System.out.println(response.isCommitted());
    System.out.println(contentType);

    SortingOrder sort = (sortParam != null)
        ? SortingOrder.valueOf(sortParam.toUpperCase())
        : SortingOrder.ASC;

    return personService.getPeople(sort);

  }

  @GetMapping("{id}")
  public ResponseEntity<Person> getPersonById(@Positive @Valid @PathVariable("id") Integer id) {

    Person person = personService.getPersonById(id);
    // return ResponseEntity.status(200).body(person);
    return ResponseEntity.ok().body(person);
  }

  @PostMapping()
  public ResponseEntity<Person> addPerson(@Valid @RequestBody NewPersonRequest person) {

    // Set<ConstraintViolation<NewPersonRequest>> validate =
    // validator.validate(person);

    // validate.forEach(error -> System.out.println(error.getMessage()));

    // if (!validate.isEmpty()) {
    // throw new ConstraintViolationException(validate);
    // }

    Person createdPerson = personService.addPerson(person);
    return ResponseEntity.status(201).body(createdPerson);
  }

  @DeleteMapping("{id}")
  public void deletePersonById(@Positive @Valid @PathVariable("id") Integer id) {
    personService.deletePersonById(id);
  }

  @PutMapping("{id}")
  public void updatePerson(@PathVariable("id") Integer id, @Valid @RequestBody PersonUpdateRequest request) {
    personService.updatePerson(id, request);
  }

  // Get all publications read by a specific person
  @GetMapping("/{personId}/publications")
  public ResponseEntity<Set<Publication>> getPersonReadPublications(@PathVariable Integer personId) {
    Person person = personRepository.findPersonWithPublications(personId);
    if (person == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(person.getReadPublications());
  }

  // Get all people who have read a specific publication
  @GetMapping("/readers/{publicationId}")
  public ResponseEntity<List<Person>> getPeopleWhoReadPublication(@PathVariable Integer publicationId) {
    List<Person> readers = personRepository.findPeopleWhoReadPublication(publicationId);
    return ResponseEntity.ok(readers);
  }

  // Get people who have read more than X publications
  @GetMapping("/avid-readers")
  public ResponseEntity<List<Person>> getAvidReaders(@RequestParam(defaultValue = "5") Integer minBooks) {
    List<Person> avidReaders = personRepository.findPeopleWhoReadMoreThan(minBooks);
    return ResponseEntity.ok(avidReaders);
  }

}
