package com.alex.person;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record PersonUpdateRequest(
    @NotEmpty String name,
    @Min(16) Integer age) {
}
