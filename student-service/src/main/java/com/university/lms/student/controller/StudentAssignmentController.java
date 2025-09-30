package com.university.lms.student.controller;

import com.university.lms.common.dto.ApiResponse;
import com.university.lms.common.model.Assignment;
import com.university.lms.student.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/assignments")
@CrossOrigin(origins = "*")
public class StudentAssignmentController {
    
    @Autowired
    private AssignmentService assignmentService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Assignment>>> getAllActiveAssignments() {
        List<Assignment> assignments = assignmentService.getActiveAssignments();
        return ResponseEntity.ok(ApiResponse.success(assignments));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Assignment>> getAssignmentById(@PathVariable String id) {
        Assignment assignment = assignmentService.getAssignmentById(id);
        return ResponseEntity.ok(ApiResponse.success(assignment));
    }
    
    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse<List<Assignment>>> getAssignmentsByCourse(@PathVariable String courseId) {
        List<Assignment> assignments = assignmentService.getAssignmentsByCourse(courseId);
        return ResponseEntity.ok(ApiResponse.success(assignments));
    }
}