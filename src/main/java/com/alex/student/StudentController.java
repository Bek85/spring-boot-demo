package com.alex.student;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;

  @Autowired
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public List<Student> getStudents() {
    return studentService.getStudents();
  }

  @PostMapping
  public ResponseEntity<Student> addStudent(@RequestBody Student student) {
    Student savedStudent = studentService.addStudent(student);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
  }

  @GetMapping("{studentId}")
  public Student getStudentById(@PathVariable("studentId") Long id) {
    return studentService.findStudentById(id);
  }

  @GetMapping("query")
  public List<Student> getStudentsByFirstNameAndAge(@RequestParam("firstName") String firstName,
      @RequestParam("age") Integer age) {
    return studentService.findStudentsByFirstNameEqualsAndAgeEquals(firstName, age);

  }
}
