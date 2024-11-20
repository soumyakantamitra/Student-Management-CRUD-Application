package com.studentmanagement.restapi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.studentmanagement.restapi.entity.Student;

public interface StudentService {
    
    List<Student> getAllStudents();
    Student getStudentById(int id);
    Student saveStudent(Student student);
    Student updateStudent(Student student, int id);
    void deleteEmployee(int id);
    Page<Student> findPaginatedAndSorted(int pageNo, int pageSize, String sortField, String sortDirection);

}
