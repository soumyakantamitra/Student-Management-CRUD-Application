package com.studentmanagement.restapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.studentmanagement.restapi.entity.Student;
import com.studentmanagement.restapi.exception.ResourceNotFoundException;
import com.studentmanagement.restapi.repository.StudentRepository;
import com.studentmanagement.restapi.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;


    public StudentServiceImpl(StudentRepository studentRepository) {
        super();
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        //checking whether the student with the given id exists or not
        if(student.isPresent()) {
            return student.get();
        } else {
            throw new ResourceNotFoundException("Student", "Id", id);
        }

        //alternative approach using lambda expression
        //return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student, int id) {
        
        //checking whether the student with the given id exists or not
        Student existingStudent = studentRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Student", "Id", id));

        existingStudent.setName(student.getName());
        existingStudent.setPercentage(student.getPercentage());
        existingStudent.setBranch(student.getBranch());
        
        //save the updated details to Database
        studentRepository.save(existingStudent);
        return existingStudent;

    }

    @Override
    public void deleteEmployee(int id) {
        
        studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));

        studentRepository.deleteById(id);
    }

    @Override
    public Page<Student> findPaginatedAndSorted(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
            : Sort.by(sortField).descending();
        
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return studentRepository.findAll(pageable);
    }
    

}
