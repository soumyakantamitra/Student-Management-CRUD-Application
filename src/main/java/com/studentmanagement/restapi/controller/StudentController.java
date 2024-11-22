package com.studentmanagement.restapi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.restapi.entity.Student;
import com.studentmanagement.restapi.service.StudentService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    //method to get all the students
    //localhost:8080/students
    @GetMapping("/students")
    public List<Student> getAllStudents() {

        return studentService.getAllStudents();
    }

    //method to get a student's details by id
    //localhost:8080/students/1
    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable("id") int studentId) {
       System.out.println("Inside the method of getStudent of StudentController: ");
        return studentService.getStudentById(studentId);
    }

    //method to add new student details
    //localhost:8080/students/add
    @PostMapping("/student/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void CreateStudent(@RequestBody Student student) {
        
        studentService.saveStudent(student);
    }

    //method to update student details
    //localhost:8080/student/update/1
    @PutMapping("/student/update/{id}")
    public Student updateStudent(@PathVariable("id") int studentId, @RequestBody Student student ) {

        return studentService.updateStudent(student, studentId);
    }

    //method to delete student details
    @DeleteMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable("id") int studentId) {
        
        //delete student from Database
        studentService.deleteEmployee(studentId);

        return "Student deleted successfully";
    }

    @GetMapping("/students/page/{pageNo}")
    public Page<Student> getPaginatedStudents(
            @PathVariable ("pageNo") int pageNo,
            @RequestParam(defaultValue = "rollNo") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        int pageSize = 5;

        Page<Student> page = studentService.findPaginatedAndSorted(pageNo, pageSize, sortField, sortDirection);
        return page;
    }
    
}
