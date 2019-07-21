package com.springboot.demo.department.service;

import com.springboot.demo.department.entity.Department;
import java.util.Collection;

public interface DepartmentService {

    Collection<Department> getAllDepartments();

    Department getDepartmentById(int id);

    void addDepartment(Department department);

    void updateDepartmentById(int id, Department department);

    void deleteDepartmentById(int id);

}
