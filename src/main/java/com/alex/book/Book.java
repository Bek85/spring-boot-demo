package com.alex.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "book")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotEmpty
  private String title;

  @NotEmpty
  private String author;

  @Min(1000)
  @Column(name = "publication_year")
  private Integer year;

  @NotEmpty
  private String isbn;

  @JsonIgnore
  private String publisherCode;

  // Default constructor needed by JPA
  public Book() {
  }

  // Constructor with id
  public Book(Integer id, String title, String author, Integer year, String isbn, String publisherCode) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.year = year;
    this.isbn = isbn;
    this.publisherCode = publisherCode;
  }

  // Constructor without id
  public Book(String title, String author, Integer year, String isbn, String publisherCode) {
    this.title = title;
    this.author = author;
    this.year = year;
    this.isbn = isbn;
    this.publisherCode = publisherCode;
  }

  // Getters and setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getPublisherCode() {
    return publisherCode;
  }

  public void setPublisherCode(String publisherCode) {
    this.publisherCode = publisherCode;
  }
}
