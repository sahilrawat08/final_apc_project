package com.university.lms.student.repository;

import com.university.lms.common.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
    
    List<Course> findByStatus(Course.CourseStatus status);
    
    List<Course> findByEnrolledStudentsContaining(String studentId);
}