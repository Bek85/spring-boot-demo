package com.alex.person;

import com.alex.validation.Foo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewPersonRequest(
    @NotEmpty @Foo String name,
    @Min(16) Integer age,
    @NotNull Gender gender,
    @JsonIgnore String password) {
}
