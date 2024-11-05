package com.alex.publication;

import com.alex.SortingOrder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/publications")
public class PublicationController {
  private final PublicationService publicationService;
  private final PublicationRepository publicationRepository;

  public PublicationController(PublicationService publicationService, PublicationRepository publicationRepository) {
    this.publicationService = publicationService;
    this.publicationRepository = publicationRepository;
  }

  @GetMapping
  public List<Publication> getBooks(
      @RequestParam(value = "sort", required = false, defaultValue = "DESC") SortingOrder sort) {
    return publicationService.getBooks(sort);
  }

  @GetMapping("{id}")
  public ResponseEntity<Publication> getBookById(@PathVariable("id") Integer id) {
    Publication publication = publicationService.getBookById(id);
    return ResponseEntity.ok().body(publication);
  }

  @PostMapping
  public ResponseEntity<Publication> addBook(@Valid @RequestBody NewPublicationRequest request) {
    Publication createdPublication = publicationService.addBook(request);
    return ResponseEntity.status(201).body(createdPublication);
  }

  @DeleteMapping("{id}")
  public void deleteBookById(@PathVariable("id") Integer id) {
    publicationService.deleteBookById(id);
  }

  @PutMapping("{id}")
  public void updateBook(
      @PathVariable("id") Integer id,
      @Valid @RequestBody PublicationUpdateRequest request) {
    publicationService.updateBook(id, request);
  }

  // Get all publications read by a specific person
  @GetMapping("/read-by/{personId}")
  public ResponseEntity<List<Publication>> getPublicationsReadByPerson(@PathVariable Integer personId) {
    List<Publication> publications = publicationRepository.findPublicationsReadByPerson(personId);
    return ResponseEntity.ok(publications);
  }

  // Get popular publications (read by at least X people)
  @GetMapping("/popular")
  public ResponseEntity<List<Publication>> getPopularPublications(
      @RequestParam(defaultValue = "3") Integer minReaders) {
    List<Publication> popularPublications = publicationRepository.findPublicationsWithMinimumReaders(minReaders);
    return ResponseEntity.ok(popularPublications);
  }
}
