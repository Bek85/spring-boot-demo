package com.alex.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

  // ! JPQL Query
  @Query("SELECT s FROM student s WHERE s.email = ?1")
  Optional<Student> findByEmail(String email);

  // ! Native Query
  // @Query(value = "SELECT * FROM student s WHERE s.email = ?1", nativeQuery =
  // true)
  // Optional<Student> findByEmail(String email);

  @Query("SELECT s FROM student s WHERE s.firstName = ?1 AND s.age = ?2")
  List<Student> findStudentsByFirstNameEqualsAndAgeEquals(String firstName, Integer age);

}
