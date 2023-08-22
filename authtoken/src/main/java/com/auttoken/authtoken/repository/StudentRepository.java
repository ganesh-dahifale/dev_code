package com.auttoken.authtoken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auttoken.authtoken.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

    Student findByUsername(String username);

    
}
