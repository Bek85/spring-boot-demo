package com.alex.person;

import com.alex.validation.Foo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewPersonRequest(
    @NotEmpty(message = "Name is required") String name,
    @Min(value = 16, message = "Age must be at least 16") Integer age,
    @NotNull(message = "Gender is required") Gender gender,
    @Email(message = "Email is invalid") String email,
    @JsonIgnore String password) {
}
