package com.springboot.demo.student.controller;

import com.springboot.demo.student.entity.Student;
import com.springboot.demo.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/departments/{departmentId}/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Collection<Student> getAllStudents(@PathVariable("departmentId") int departmentId) {
        return studentService.getAllStudents(departmentId);
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable("departmentId") int departmentId, @PathVariable("studentId") int studentId) {
        return studentService.getStudentById(departmentId, studentId);
    }

    @PostMapping
    public void addStudent(@PathVariable("departmentId") int departmentId, @RequestBody Student student) {
        studentService.addStudent(departmentId, student);
    }

    @PutMapping("/{studentId}")
    public void updateStudentById(@PathVariable("departmentId") int departmentId, @PathVariable("studentId") int studentId, @RequestBody Student student) {
        studentService.updateStudentById(departmentId, studentId, student);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudentById(@PathVariable("departmentId") int departmentId, @PathVariable("studentId") int studentId) {
        studentService.deleteStudentById(departmentId, studentId);
    }

}
