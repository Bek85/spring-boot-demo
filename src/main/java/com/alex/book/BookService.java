package com.alex.book;

import com.alex.SortingOrder;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<Book> getBooks(SortingOrder sort) {
    if (sort == SortingOrder.ASC) {
      return bookRepository.getBooks().stream()
          .sorted(Comparator.comparing(Book::id))
          .collect(Collectors.toList());
    }
    return bookRepository.getBooks().stream()
        .sorted(Comparator.comparing(Book::id).reversed())
        .collect(Collectors.toList());
  }

  public Book addBook(Book book) {
    Book newBook = new Book(
        bookRepository.getIdCounter().incrementAndGet(),
        book.title(),
        book.author(),
        book.year(),
        book.isbn(),
        book.publisherCode());
    bookRepository.getBooks().add(newBook);
    return newBook;
  }

  public Optional<Book> getBookById(Integer id) {
    return bookRepository.getBooks().stream()
        .filter(book -> book.id().equals(id))
        .findFirst();
  }

  public void deleteBookById(Integer id) {
    bookRepository.getBooks().removeIf(book -> book.id().equals(id));
  }

  public void updateBook(Integer id, BookUpdateRequest request) {
    bookRepository.getBooks().stream()
        .filter(b -> b.id().equals(id))
        .findFirst()
        .ifPresent(b -> {
          var index = bookRepository.getBooks().indexOf(b);
          Book updatedBook = new Book(
              b.id(),
              request.title() != null ? request.title() : b.title(),
              request.author() != null ? request.author() : b.author(),
              request.year() != null ? request.year() : b.year(),
              request.isbn() != null ? request.isbn() : b.isbn(),
              b.publisherCode());
          bookRepository.getBooks().set(index, updatedBook);
        });
  }
}
