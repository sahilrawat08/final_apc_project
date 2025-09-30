package com.university.lms.admin.controller;

import com.university.lms.common.dto.ApiResponse;
import com.university.lms.common.model.Submission;
import com.university.lms.admin.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/submissions")
@CrossOrigin(origins = "*")
public class AdminSubmissionController {
    
    @Autowired
    private SubmissionService submissionService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Submission>>> getAllSubmissions() {
        List<Submission> submissions = submissionService.getAllSubmissions();
        return ResponseEntity.ok(ApiResponse.success(submissions));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Submission>> getSubmissionById(@PathVariable String id) {
        Submission submission = submissionService.getSubmissionById(id);
        return ResponseEntity.ok(ApiResponse.success(submission));
    }
    
    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<ApiResponse<List<Submission>>> getSubmissionsByAssignment(@PathVariable String assignmentId) {
        List<Submission> submissions = submissionService.getSubmissionsByAssignment(assignmentId);
        return ResponseEntity.ok(ApiResponse.success(submissions));
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<Submission>>> getSubmissionsByStudent(@PathVariable String studentId) {
        List<Submission> submissions = submissionService.getSubmissionsByStudent(studentId);
        return ResponseEntity.ok(ApiResponse.success(submissions));
    }
    
    @PutMapping("/{id}/grade")
    public ResponseEntity<ApiResponse<Submission>> gradeSubmission(@PathVariable String id, 
            @RequestBody Map<String, Object> gradeData) {
        Integer grade = (Integer) gradeData.get("grade");
        String feedback = (String) gradeData.get("feedback");
        
        Submission gradedSubmission = submissionService.gradeSubmission(id, grade, feedback);
        return ResponseEntity.ok(ApiResponse.success("Submission graded successfully", gradedSubmission));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSubmission(@PathVariable String id) {
        submissionService.deleteSubmission(id);
        return ResponseEntity.ok(ApiResponse.success("Submission deleted successfully", null));
    }
}