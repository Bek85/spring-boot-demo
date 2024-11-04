package com.alex.book;

import com.alex.SortingOrder;
import com.alex.exception.DuplicateResourceException;
import com.alex.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<Book> getBooks(SortingOrder sort) {
    return bookRepository.findAll();
  }

  public Book addBook(NewBookRequest request) {
    if (bookRepository.existsByIsbn(request.isbn())) {
      throw new DuplicateResourceException(
          "Book with ISBN " + request.isbn() + " already exists");
    }

    Book book = new Book(
        request.title(),
        request.author(),
        request.year(),
        request.isbn(),
        request.publisherCode());

    return bookRepository.save(book);
  }

  public Book getBookById(Integer id) {
    return bookRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Book with id " + id + " not found"));
  }

  public void deleteBookById(Integer id) {
    if (!bookRepository.existsById(id)) {
      throw new ResourceNotFoundException(
          "Book with id " + id + " not found");
    }
    bookRepository.deleteById(id);
  }

  public void updateBook(Integer id, BookUpdateRequest request) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Book with id " + id + " not found"));

    // Check if ISBN is being updated and if it already exists
    if (request.isbn() != null &&
        !request.isbn().equals(book.getIsbn()) &&
        bookRepository.existsByIsbn(request.isbn())) {
      throw new DuplicateResourceException(
          "Book with ISBN " + request.isbn() + " already exists");
    }

    if (request.title() != null && !request.title().isEmpty()) {
      book.setTitle(request.title());
    }
    if (request.author() != null && !request.author().isEmpty()) {
      book.setAuthor(request.author());
    }
    if (request.year() != null) {
      book.setYear(request.year());
    }
    if (request.isbn() != null && !request.isbn().isEmpty()) {
      book.setIsbn(request.isbn());
    }

    bookRepository.save(book);
  }
}
