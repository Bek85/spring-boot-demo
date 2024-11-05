package com.alex.student;

import com.alex.exception.DuplicateResourceException;
import com.alex.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  public Student addStudent(Student student) {
    if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
      throw new DuplicateResourceException("Email already taken");
    }
    return studentRepository.save(student);
  }

  public Student findStudentById(Long id) {
    return studentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Student with id [%s] not found".formatted(id)));
  }
}
