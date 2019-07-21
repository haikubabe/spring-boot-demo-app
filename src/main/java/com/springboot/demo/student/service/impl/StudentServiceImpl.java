package com.springboot.demo.student.service.impl;

import com.springboot.demo.department.entity.Department;
import com.springboot.demo.department.exception.DepartmentNotFoundException;
import com.springboot.demo.department.repository.DepartmentRepository;
import com.springboot.demo.student.entity.Student;
import com.springboot.demo.student.exception.StudentCourseEmptyException;
import com.springboot.demo.student.exception.StudentNameEmptyException;
import com.springboot.demo.student.exception.StudentNotFoundException;
import com.springboot.demo.student.repository.StudentRepository;
import com.springboot.demo.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Collection<Student> getAllStudents(int departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        department.orElseThrow(() -> new DepartmentNotFoundException("department with id " + departmentId + " is not found"));
        List<Student> students = studentRepository.findByDepartmentId(departmentId);
        if (students.size() == 0) {
            throw new StudentNotFoundException("department with id " + departmentId + " doesn't contain any students");
        }
        return students;
    }

    @Override
    public Student getStudentById(int departmentId, int studentId) {
        Optional<Student> s = studentRepository.findById(studentId);
        Student student = s.orElseThrow(() -> new StudentNotFoundException("student with id " + studentId + " is not found"));
        Optional<Department> d = departmentRepository.findById(departmentId);
        d.orElseThrow(() -> new DepartmentNotFoundException("department with id " + departmentId + " is not found"));
        if (student.getDepartment().getId() != departmentId) {
            throw new StudentNotFoundException("student with id " + studentId + " is not found in the department with id " + departmentId);
        }
        return student;
    }

    @Override
    public void addStudent(int departmentId, Student student) {
        Optional<Department> d = departmentRepository.findById(departmentId);
        Department department = d.orElseThrow(() -> new DepartmentNotFoundException("department with id " + departmentId + " is not found"));
        if (student.getName() == null || student.getName().isEmpty()) {
            throw new StudentNameEmptyException("student name cannot be empty");
        }
        if (student.getCourse() == null || student.getCourse().isEmpty()) {
            throw new StudentCourseEmptyException("student course cannot be empty");
        }
        department.addOrUpdateStudent(student);
        studentRepository.save(student);
    }

    @Override
    public void updateStudentById(int departmentId, int studentId, Student student) {
        Optional<Student> s = studentRepository.findById(studentId);
        Student oldStudent = s.orElseThrow(() -> new StudentNotFoundException("student with id " + studentId + " is not found"));
        Optional<Department> d = departmentRepository.findById(departmentId);
        Department department = d.orElseThrow(() -> new DepartmentNotFoundException("department with id " + departmentId + " is not found"));
        if (student.getName() == null || student.getName().isEmpty()) {
            throw new StudentNameEmptyException("student name cannot be empty");
        }
        if (student.getCourse() == null || student.getCourse().isEmpty()) {
            throw new StudentCourseEmptyException("student course cannot be empty");
        }
        if (oldStudent.getDepartment().getId() != departmentId) {
            oldStudent.getDepartment().removeStudent(oldStudent);
        }
        oldStudent.setName(student.getName());
        oldStudent.setCourse(student.getCourse());
        department.addOrUpdateStudent(oldStudent);
        studentRepository.save(oldStudent);
    }

    @Override
    public void deleteStudentById(int departmentId, int studentId) {
        Optional<Student> s = studentRepository.findById(studentId);
        Student student = s.orElseThrow(() -> new StudentNotFoundException("student with id " + studentId + " is not found"));
        Optional<Department> d = departmentRepository.findById(departmentId);
        Department department = d.orElseThrow(() -> new DepartmentNotFoundException("department with id " + departmentId + " is not found"));
        if (student.getDepartment().getId() != departmentId) {
            throw new StudentNotFoundException("student with id " + studentId + " is not found in the department with id " + departmentId);
        }
        department.removeStudent(student);
        studentRepository.deleteById(studentId);
    }
}
