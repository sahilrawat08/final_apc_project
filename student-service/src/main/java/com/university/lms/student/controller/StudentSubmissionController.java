package com.university.lms.student.controller;

import com.university.lms.common.dto.ApiResponse;
import com.university.lms.common.model.Submission;
import com.university.lms.student.service.SubmissionService;
import com.university.lms.student.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/student/submissions")
@CrossOrigin(origins = "*")
public class StudentSubmissionController {
    
    @Autowired
    private SubmissionService submissionService;
    
    @Autowired
    private StudentProfileService studentProfileService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Submission>>> getMySubmissions(Authentication authentication) {
        String userId = (String) authentication.getDetails();
        String studentId = studentProfileService.getStudentByUserId(userId).getId();
        List<Submission> submissions = submissionService.getSubmissionsByStudent(studentId);
        return ResponseEntity.ok(ApiResponse.success(submissions));
    }
    
    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<ApiResponse<Submission>> getSubmissionForAssignment(@PathVariable String assignmentId, 
            Authentication authentication) {
        String userId = (String) authentication.getDetails();
        String studentId = studentProfileService.getStudentByUserId(userId).getId();
        Optional<Submission> submission = submissionService.getSubmissionByAssignmentAndStudent(assignmentId, studentId);
        return ResponseEntity.ok(ApiResponse.success(submission.orElse(null)));
    }
    
    @PostMapping("/assignment/{assignmentId}")
    public ResponseEntity<ApiResponse<Submission>> submitAssignment(@PathVariable String assignmentId, 
            @RequestBody Map<String, String> submissionData, Authentication authentication) {
        String userId = (String) authentication.getDetails();
        String studentId = studentProfileService.getStudentByUserId(userId).getId();
        String studentName = authentication.getName();
        String content = submissionData.get("content");
        
        Submission submission = submissionService.submitAssignment(assignmentId, studentId, studentName, content);
        return ResponseEntity.ok(ApiResponse.success("Assignment submitted successfully", submission));
    }
    
    @PutMapping("/{submissionId}")
    public ResponseEntity<ApiResponse<Submission>> updateSubmission(@PathVariable String submissionId, 
            @RequestBody Map<String, String> submissionData, Authentication authentication) {
        String userId = (String) authentication.getDetails();
        String studentId = studentProfileService.getStudentByUserId(userId).getId();
        String content = submissionData.get("content");
        
        Submission submission = submissionService.updateSubmission(submissionId, studentId, content);
        return ResponseEntity.ok(ApiResponse.success("Submission updated successfully", submission));
    }
}