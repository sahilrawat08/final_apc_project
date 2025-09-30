package com.university.lms.admin.service;

import com.university.lms.common.model.Submission;
import com.university.lms.common.exception.ResourceNotFoundException;
import com.university.lms.admin.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionService {
    
    @Autowired
    private SubmissionRepository submissionRepository;
    
    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }
    
    public Submission getSubmissionById(String id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found with id: " + id));
    }
    
    public List<Submission> getSubmissionsByAssignment(String assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId);
    }
    
    public List<Submission> getSubmissionsByStudent(String studentId) {
        return submissionRepository.findByStudentId(studentId);
    }
    
    public Submission gradeSubmission(String submissionId, Integer grade, String feedback) {
        Submission submission = getSubmissionById(submissionId);
        
        submission.setGrade(grade);
        submission.setFeedback(feedback);
        submission.setStatus(Submission.SubmissionStatus.GRADED);
        submission.setGradedAt(LocalDateTime.now());
        
        return submissionRepository.save(submission);
    }
    
    public void deleteSubmission(String id) {
        Submission submission = getSubmissionById(id);
        submissionRepository.delete(submission);
    }
}