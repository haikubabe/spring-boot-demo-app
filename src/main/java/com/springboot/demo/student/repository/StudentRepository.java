package com.springboot.demo.student.repository;

import com.springboot.demo.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByDepartmentId(int departmentId);

}
