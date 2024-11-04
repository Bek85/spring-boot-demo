package com.alex.book;

import jakarta.validation.constraints.Min;

public record BookUpdateRequest(
    String title,
    String author,
    @Min(1000) Integer year,
    String isbn) {
}
