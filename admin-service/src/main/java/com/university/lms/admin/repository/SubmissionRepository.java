package com.university.lms.admin.repository;

import com.university.lms.common.model.Submission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends MongoRepository<Submission, String> {
    
    List<Submission> findByAssignmentId(String assignmentId);
    
    List<Submission> findByStudentId(String studentId);
    
    Optional<Submission> findByAssignmentIdAndStudentId(String assignmentId, String studentId);
    
    List<Submission> findByStatus(Submission.SubmissionStatus status);
}