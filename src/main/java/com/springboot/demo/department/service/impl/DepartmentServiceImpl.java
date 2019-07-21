package com.springboot.demo.department.service.impl;

import com.springboot.demo.department.entity.Department;
import com.springboot.demo.department.exception.DepartmentNotFoundException;
import com.springboot.demo.department.repository.DepartmentRepository;
import com.springboot.demo.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public Collection<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(int id) {
        Optional<Department> d = departmentRepository.findById(id);
        return d.orElseThrow(() -> new DepartmentNotFoundException("department with id " + id + " is not found"));
    }

    @Override
    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void updateDepartmentById(int id, Department department) {
        Optional<Department> d = departmentRepository.findById(id);
        Department oldDepartment = d.orElseThrow(() -> new DepartmentNotFoundException("department with id " + id + " is not found"));
        oldDepartment.setName(department.getName());
        departmentRepository.save(oldDepartment);
    }

    @Override
    public void deleteDepartmentById(int id) {
        Optional<Department> d = departmentRepository.findById(id);
        d.orElseThrow(() -> new DepartmentNotFoundException("department with id " + id + " is not found"));
        departmentRepository.deleteById(id);
    }

}
