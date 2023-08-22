package com.auttoken.authtoken.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auttoken.authtoken.entity.Student;
import com.auttoken.authtoken.services.StudentService;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/getstudents")
    public List<Student> getStudent(){
        return studentService.getStudent();
    }

    @PostMapping("/register")
    public ResponseEntity<Student> savStudent(@RequestBody Student student){
        Student new_student = null;
        ResponseEntity response = null;
        try{
            String hashPassword = passwordEncoder.encode(student.getPassword());
            student.setPassword(hashPassword);
            new_student = studentService.savStudent(student);
            if(new_student.getId()>0){
                response = ResponseEntity.status(HttpStatus.CREATED)
                .body("Successfully Registered");
            }
        
        }catch(Exception e){
            response = ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE)
            .body("Exception Occured due to "+ e.getMessage());
        }
        return response;

    }

    @GetMapping("/home")
    public String getHome(){
        return "Welcome to Dashboard";
    }

    @GetMapping("/home/{id}")
    public Student getAlStudent(@PathVariable int id){
        return studentService.getAllStudents(id);
    }
    
}
