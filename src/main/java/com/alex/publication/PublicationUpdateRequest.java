package com.alex.publication;

import jakarta.validation.constraints.Min;

public record PublicationUpdateRequest(
    String title,
    String author,
    @Min(1000) Integer year,
    String isbn) {
}
