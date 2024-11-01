package com.alex.person;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record Person(Integer id,
    String name,
    Integer age,
    Gender gender,
    @JsonIgnore String password) {

}
