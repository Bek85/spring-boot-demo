package com.alex.person;

import com.fasterxml.jackson.annotation.JsonGetter;

public record Person(Integer id,
                     @JsonGetter("firstName") String name,
                     Integer age,
                     Gender gender) {
}