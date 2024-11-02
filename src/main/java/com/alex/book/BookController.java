package com.alex.book;

import com.alex.SortingOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public List<Book> getBooks(
      @RequestParam(value = "sort", required = false, defaultValue = "DESC") SortingOrder sort) {
    return bookService.getBooks(sort);
  }

  @GetMapping("{id}")
  public ResponseEntity<Optional<Book>> getBookById(@PathVariable("id") Integer id) {
    Optional<Book> book = bookService.getBookById(id);
    return ResponseEntity.ok().body(book);
  }

  @PostMapping
  public ResponseEntity<Book> addBook(@RequestBody Book book) {
    Book createdBook = bookService.addBook(book);
    return ResponseEntity.status(201).body(createdBook);
  }

  @DeleteMapping("{id}")
  public void deleteBookById(@PathVariable("id") Integer id) {
    bookService.deleteBookById(id);
  }

  @PutMapping("{id}")
  public void updateBook(@PathVariable("id") Integer id, @RequestBody BookUpdateRequest request) {
    bookService.updateBook(id, request);
  }
}
