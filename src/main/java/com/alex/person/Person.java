package com.alex.person;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record Person(Integer id,
                     @JsonGetter("firstName") String name,
                     @JsonIgnore Integer age,
                     Gender gender) {
}