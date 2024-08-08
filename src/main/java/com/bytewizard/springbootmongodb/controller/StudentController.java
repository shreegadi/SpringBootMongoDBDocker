package com.bytewizard.springbootmongodb.controller;

import com.bytewizard.springbootmongodb.entity.Student;
import com.bytewizard.springbootmongodb.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/getAllStudent")
    private List<Student> getStudents(){
        return studentRepo.findAll();
    }

    @GetMapping(value = "/getById/{id}")
    private Student getStudentById(@PathVariable int id){
        return studentRepo.findById(id).get();
    }

    @PostMapping("/create")
    private Student createStudent(@RequestBody Student student){
        return studentRepo.save(student);
    }

}
