package com.alex.book;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record Book(
    Integer id,
    String title,
    String author,
    Integer year,
    String isbn,
    @JsonIgnore String publisherCode) {
}
