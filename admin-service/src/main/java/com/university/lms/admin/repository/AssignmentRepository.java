package com.university.lms.admin.repository;

import com.university.lms.common.model.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends MongoRepository<Assignment, String> {
    
    List<Assignment> findByCourseId(String courseId);
    
    List<Assignment> findByStatus(Assignment.AssignmentStatus status);
}