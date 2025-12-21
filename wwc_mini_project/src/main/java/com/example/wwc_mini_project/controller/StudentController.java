package com.example.wwc_mini_project.controller;

import com.example.wwc_mini_project.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.wwc_mini_project.model.Student;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        if(student.getName() == null || student.getName().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is a Required Field");
        }

        if(studentService.existsId(student.getId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Student with same ID already exists");
        }

        Student saveStudent = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveStudent);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        if(student == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Found");
        }

        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable int id) {
        boolean deleted = studentService.deleteById(id);
        if(!deleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not Found");
        }

        return ResponseEntity.ok("Student Deleted Successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchByCourse(@RequestParam String name) {
        return ResponseEntity.ok(studentService.getStudentsByCourse(name));
    }
}
