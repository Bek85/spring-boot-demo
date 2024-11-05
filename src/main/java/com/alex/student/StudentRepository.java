package com.alex.student;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
  Optional<Student> findByEmail(String email);

  List<Student> findStudentsByFirstNameEqualsAndAgeEquals(String firstName, Integer age);
}
