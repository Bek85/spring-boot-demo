package com.alex.publication;

import com.alex.SortingOrder;
import com.alex.exception.DuplicateResourceException;
import com.alex.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PublicationService {
  private final PublicationRepository publicationRepository;

  public PublicationService(PublicationRepository publicationRepository) {
    this.publicationRepository = publicationRepository;
  }

  public List<Publication> getBooks(SortingOrder sort) {
    return publicationRepository.findAll();
  }

  public Publication addBook(NewPublicationRequest request) {
    if (publicationRepository.existsByIsbn(request.isbn())) {
      throw new DuplicateResourceException(
          "Book with ISBN " + request.isbn() + " already exists");
    }

    Publication publication = new Publication(
        request.title(),
        request.author(),
        request.year(),
        request.isbn(),
        request.publisherCode());

    return publicationRepository.save(publication);
  }

  public Publication getBookById(Integer id) {
    return publicationRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Book with id " + id + " not found"));
  }

  public void deleteBookById(Integer id) {
    if (!publicationRepository.existsById(id)) {
      throw new ResourceNotFoundException(
          "Book with id " + id + " not found");
    }
    publicationRepository.deleteById(id);
  }

  public void updateBook(Integer id, PublicationUpdateRequest request) {
    Publication publication = publicationRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Book with id " + id + " not found"));

    // Check if ISBN is being updated and if it already exists
    if (request.isbn() != null &&
        !request.isbn().equals(publication.getIsbn()) &&
        publicationRepository.existsByIsbn(request.isbn())) {
      throw new DuplicateResourceException(
          "Book with ISBN " + request.isbn() + " already exists");
    }

    if (request.title() != null && !request.title().isEmpty()) {
      publication.setTitle(request.title());
    }
    if (request.author() != null && !request.author().isEmpty()) {
      publication.setAuthor(request.author());
    }
    if (request.year() != null) {
      publication.setYear(request.year());
    }
    if (request.isbn() != null && !request.isbn().isEmpty()) {
      publication.setIsbn(request.isbn());
    }

    publicationRepository.save(publication);
  }
}
