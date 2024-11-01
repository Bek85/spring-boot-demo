package com.alex.person;

import com.alex.SortingOrder;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

  private final PersonService personService;
  private final Validator validator;

  public PersonController(PersonService personService, Validator validator) {
    this.personService = personService;
    this.validator = validator;
  }

  @GetMapping()
  public List<Person> getPeople(
      HttpMethod httpMethod,
      ServletRequest request,
      ServletResponse response,
      @RequestHeader("Content-Type") String contentType,
      @RequestParam(value = "sort", required = false, defaultValue = "DESC") SortingOrder sort,
      @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
    System.out.println(httpMethod);
    System.out.println(request.getLocalAddr());
    System.out.println(response.isCommitted());
    System.out.println(contentType);

    return personService.getPeople(sort);
  }

  @GetMapping("{id}")
  public ResponseEntity<Optional<Person>> getPersonById(@Positive @Valid @PathVariable("id") Integer id) {

    Optional<Person> person = personService.getPersonById(id);
    // return ResponseEntity.status(200).body(person);
    return ResponseEntity.ok().body(person);
  }

  @PostMapping()
  public ResponseEntity<Person> addPerson(@RequestBody NewPersonRequest person) {

    Set<ConstraintViolation<NewPersonRequest>> validate = validator.validate(person);

    validate.forEach(error -> System.out.println(error.getMessage()));

    if (!validate.isEmpty()) {
      throw new ConstraintViolationException(validate);
    }

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

}
