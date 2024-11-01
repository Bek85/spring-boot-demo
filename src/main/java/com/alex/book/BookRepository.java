package com.alex.book;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class BookRepository {
  private final AtomicInteger idCounter = new AtomicInteger(0);
  private final List<Book> books = new ArrayList<>();

  {
    books.add(new Book(idCounter.incrementAndGet(), "The Great Gatsby", "F. Scott Fitzgerald", 1925, "978-0743273565",
        "PUB001"));
    books.add(
        new Book(idCounter.incrementAndGet(), "To Kill a Mockingbird", "Harper Lee", 1960, "978-0446310789", "PUB002"));
    books.add(new Book(idCounter.incrementAndGet(), "1984", "George Orwell", 1949, "978-0451524935", "PUB003"));
    books.add(
        new Book(idCounter.incrementAndGet(), "Pride and Prejudice", "Jane Austen", 1813, "978-0141439518", "PUB004"));
  }

  public AtomicInteger getIdCounter() {
    return idCounter;
  }

  public List<Book> getBooks() {
    return books;
  }
}
