package com.springboot.demo.student.service;

import com.springboot.demo.student.entity.Student;
import java.util.Collection;

public interface StudentService {

    Collection<Student> getAllStudents(int departmentId);

    Student getStudentById(int departmentId, int studentId);

    void addStudent(int departmentId, Student student);

    void updateStudentById(int departmentId, int studentId, Student student);

    void deleteStudentById(int departmentId,int studentId);

}
