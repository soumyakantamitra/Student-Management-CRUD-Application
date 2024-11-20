package com.studentmanagement.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentmanagement.restapi.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
    
}
