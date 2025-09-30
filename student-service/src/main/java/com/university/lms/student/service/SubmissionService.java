package com.university.lms.student.service;

import com.university.lms.common.model.Submission;
import com.university.lms.common.model.Assignment;
import com.university.lms.common.exception.ResourceNotFoundException;
import com.university.lms.common.exception.BadRequestException;
import com.university.lms.student.repository.SubmissionRepository;
import com.university.lms.student.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {
    
    @Autowired
    private SubmissionRepository submissionRepository;
    
    @Autowired
    private AssignmentRepository assignmentRepository;
    
    public List<Submission> getSubmissionsByStudent(String studentId) {
        return submissionRepository.findByStudentId(studentId);
    }
    
    public Optional<Submission> getSubmissionByAssignmentAndStudent(String assignmentId, String studentId) {
        return submissionRepository.findByAssignmentIdAndStudentId(assignmentId, studentId);
    }
    
    public Submission submitAssignment(String assignmentId, String studentId, String studentName, String content) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + assignmentId));
        
        // Check if assignment is still active
        if (!assignment.getStatus().equals(Assignment.AssignmentStatus.ACTIVE)) {
            throw new BadRequestException("Assignment is not accepting submissions");
        }
        
        // Check if student already submitted
        Optional<Submission> existingSubmission = submissionRepository
                .findByAssignmentIdAndStudentId(assignmentId, studentId);
        
        if (existingSubmission.isPresent()) {
            throw new BadRequestException("Assignment already submitted");
        }
        
        // Create new submission
        Submission submission = new Submission();
        submission.setAssignmentId(assignmentId);
        submission.setStudentId(studentId);
        submission.setStudentName(studentName);
        submission.setContent(content);
        submission.setSubmittedAt(LocalDateTime.now());
        
        // Check if submission is late
        if (LocalDateTime.now().isAfter(assignment.getDueDate())) {
            submission.setStatus(Submission.SubmissionStatus.LATE_SUBMISSION);
        } else {
            submission.setStatus(Submission.SubmissionStatus.SUBMITTED);
        }
        
        return submissionRepository.save(submission);
    }
    
    public Submission updateSubmission(String submissionId, String studentId, String content) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found with id: " + submissionId));
        
        if (!submission.getStudentId().equals(studentId)) {
            throw new BadRequestException("Not authorized to update this submission");
        }
        
        if (submission.getStatus().equals(Submission.SubmissionStatus.GRADED)) {
            throw new BadRequestException("Cannot update graded submission");
        }
        
        Assignment assignment = assignmentRepository.findById(submission.getAssignmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found"));
        
        if (LocalDateTime.now().isAfter(assignment.getDueDate())) {
            throw new BadRequestException("Cannot update submission after due date");
        }
        
        submission.setContent(content);
        submission.setSubmittedAt(LocalDateTime.now());
        
        return submissionRepository.save(submission);
    }
}