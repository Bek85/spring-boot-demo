package com.alex.book;

public record BookUpdateRequest(
    String title,
    String author,
    Integer year,
    String isbn) {
}
