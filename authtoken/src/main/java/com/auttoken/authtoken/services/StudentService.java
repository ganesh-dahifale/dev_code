package com.auttoken.authtoken.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auttoken.authtoken.entity.Student;
import com.auttoken.authtoken.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;


    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public Student savStudent(Student student){
        return studentRepository.save(student);
    }
    public Student getAllStudents(int id){
        return studentRepository.findById(id).orElseThrow(null);
    }
    
}
