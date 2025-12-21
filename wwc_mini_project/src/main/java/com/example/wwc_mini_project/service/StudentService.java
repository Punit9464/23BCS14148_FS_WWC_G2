package com.example.wwc_mini_project.service;

import com.example.wwc_mini_project.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudentById(int id) {
        return students.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean existsId(int id) {
        return students.stream().anyMatch(x -> x.getId() == id);
    }

    public boolean deleteById(int id) {
        return students.removeIf(x -> x.getId() == id);
    }

    public List<Student> getStudentsByCourse(String course) {
        return students.stream().filter(x -> Objects.equals(x.getCourse(), course)).collect(Collectors.toList());
    }

    public Student addStudent(Student student) {
        students.add(student);
        return student;
    }
}
