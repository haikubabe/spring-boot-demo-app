package com.springboot.demo.department.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springboot.demo.student.entity.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Student> students = new ArrayList<>();

    public Department() {

    }

    public Department(String name) {
        super();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addOrUpdateStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            student.setDepartment(this);
        } else {
            updateStudent(student);
        }
    }

    private void updateStudent(Student student) {
        for (int i=0;i<students.size();i++) {
            if (students.get(i).getId() == student.getId()) {
                students.set(i, student);
                student.setDepartment(this);
            }
        }
    }

    public void removeStudent(Student student) {
        if (students.contains(student)) {
            students.remove(student);
            student.setDepartment(null);
        }
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
