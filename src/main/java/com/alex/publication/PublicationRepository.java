package com.alex.publication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Integer> {
  // Find all publications read by a specific person
  @Query("SELECT pub FROM Publication pub JOIN pub.readers r WHERE r.id = :personId")
  List<Publication> findPublicationsReadByPerson(@Param("personId") Integer personId);

  // Find publications that have been read by at least X people
  @Query("SELECT pub FROM Publication pub WHERE SIZE(pub.readers) >= :readerCount")
  List<Publication> findPublicationsWithMinimumReaders(@Param("readerCount") Integer readerCount);

  boolean existsByIsbn(String isbn);
}
