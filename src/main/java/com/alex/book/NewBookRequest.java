package com.alex.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record NewBookRequest(
    @NotEmpty(message = "Title is required") String title,

    @NotEmpty(message = "Author is required") String author,

    @Min(value = 1000, message = "Year must be valid") Integer year,

    @NotEmpty(message = "ISBN is required") String isbn,

    String publisherCode) {
}
