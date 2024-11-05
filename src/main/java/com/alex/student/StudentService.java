package com.alex.student;

import com.alex.exception.DuplicateResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public Student addStudent(Student student) {
    // Check if email already exists
    if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
      throw new DuplicateResourceException("Email already taken");
    }
    return studentRepository.save(student);
  }
}
