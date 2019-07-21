package com.springboot.demo.department.repository;

import com.springboot.demo.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
