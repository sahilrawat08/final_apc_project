package com.university.lms.admin.repository;

import com.university.lms.common.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    
    Optional<Student> findByStudentId(String studentId);
    
    Optional<Student> findByUserId(String userId);
    
    Optional<Student> findByEmail(String email);
    
    List<Student> findByDepartment(String department);
    
    List<Student> findByEnrolledCoursesContaining(String courseId);
    
    boolean existsByStudentId(String studentId);
    
    boolean existsByEmail(String email);
}