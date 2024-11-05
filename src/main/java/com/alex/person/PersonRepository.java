package com.alex.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

  boolean existsByEmail(String email);

  // Find all publications read by a specific person
  @Query("SELECT p FROM Person p LEFT JOIN FETCH p.readPublications WHERE p.id = :personId")
  Person findPersonWithPublications(@Param("personId") Integer personId);

  // Find all people who have read a specific publication
  @Query("SELECT p FROM Person p JOIN p.readPublications pub WHERE pub.id = :publicationId")
  List<Person> findPeopleWhoReadPublication(@Param("publicationId") Integer publicationId);

  // Find people who have read more than X publications
  @Query("SELECT p FROM Person p WHERE SIZE(p.readPublications) > :count")
  List<Person> findPeopleWhoReadMoreThan(@Param("count") Integer count);

}
