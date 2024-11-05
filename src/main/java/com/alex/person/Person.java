package com.alex.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import com.alex.publication.Publication;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private Integer age;
  @Enumerated(EnumType.STRING)
  private Gender gender;
  @Email
  private String email;
  @JsonIgnore
  private String password;

  @ManyToMany
  @JoinTable(name = "person_read_publications", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "publication_id"))
  private Set<Publication> readPublications = new HashSet<>();

  // Default constructor needed by JPA, that is created by @NoArgsConstructor
  // annotation when used
  public Person() {
  }

  // All-args constructor
  public Person(Integer id, String name, Integer age, Gender gender, String email, String password) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.email = email;
    this.password = password;
  }

  public Person(String name, Integer age, Gender gender, String email, String password) {
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.email = email;
    this.password = password;
  }

  // Getters and setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Publication> getReadPublications() {
    return readPublications;
  }

  public void setReadPublications(Set<Publication> readPublications) {
    this.readPublications = readPublications;
  }

  public void addReadPublication(Publication publication) {
    readPublications.add(publication);
    publication.getReaders().add(this);
  }

  public void removeReadPublication(Publication publication) {
    readPublications.remove(publication);
    publication.getReaders().remove(this);
  }
}
