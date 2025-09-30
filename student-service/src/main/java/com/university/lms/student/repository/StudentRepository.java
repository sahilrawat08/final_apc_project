package com.university.lms.student.repository;

import com.university.lms.common.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    
    Optional<Student> findByStudentId(String studentId);
    
    Optional<Student> findByUserId(String userId);
    
    Optional<Student> findByEmail(String email);
}