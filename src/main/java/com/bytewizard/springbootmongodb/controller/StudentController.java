package com.bytewizard.springbootmongodb.controller;

import com.bytewizard.springbootmongodb.entity.Student;
import com.bytewizard.springbootmongodb.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    // Get all students
    @GetMapping("/getAllStudent")
    private List<Student> getStudents() {
        return studentRepo.findAll();
    }

    // Get student by ID
    @GetMapping(value = "/getById/{id}")
    private ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Optional<Student> student = studentRepo.findById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new student
    @PostMapping("/create")
    private Student createStudent(@RequestBody Student student) {
        return studentRepo.save(student);
    }

    // Update an existing student
    @PutMapping("/update/{id}")
    private ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student studentDetails) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setName(studentDetails.getName());
            existingStudent.setAge(studentDetails.getAge());
            // Update other fields as necessary
            Student updatedStudent = studentRepo.save(existingStudent);
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a student
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
