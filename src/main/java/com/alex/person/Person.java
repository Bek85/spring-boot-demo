package com.alex.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "person")
public record Person(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id,
    String name,
    Integer age,
    Gender gender,
    @Email String email,
    @JsonIgnore String password) {

}
