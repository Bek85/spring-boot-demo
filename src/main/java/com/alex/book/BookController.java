package com.alex.book;

import com.alex.SortingOrder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
  public ResponseEntity<Book> getBookById(@PathVariable("id") Integer id) {
    Book book = bookService.getBookById(id);
    return ResponseEntity.ok().body(book);
  }

  @PostMapping
  public ResponseEntity<Book> addBook(@Valid @RequestBody NewBookRequest request) {
    Book createdBook = bookService.addBook(request);
    return ResponseEntity.status(201).body(createdBook);
  }

  @DeleteMapping("{id}")
  public void deleteBookById(@PathVariable("id") Integer id) {
    bookService.deleteBookById(id);
  }

  @PutMapping("{id}")
  public void updateBook(
      @PathVariable("id") Integer id,
      @Valid @RequestBody BookUpdateRequest request) {
    bookService.updateBook(id, request);
  }
}
