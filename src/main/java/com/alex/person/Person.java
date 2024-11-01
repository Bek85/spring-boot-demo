package com.alex.person;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;


public record Person(Integer id,
                     String name,
                     Integer age,
                     Gender gender,
                     String password
                     ) {

}