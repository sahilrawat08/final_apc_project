package com.university.lms.student.controller;

import com.university.lms.common.dto.ApiResponse;
import com.university.lms.common.model.Student;
import com.university.lms.student.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/profile")
@CrossOrigin(origins = "*")
public class StudentProfileController {
    
    @Autowired
    private StudentProfileService studentProfileService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<Student>> getMyProfile(Authentication authentication) {
        String userId = (String) authentication.getDetails();
        Student student = studentProfileService.getStudentByUserId(userId);
        return ResponseEntity.ok(ApiResponse.success(student));
    }
    
    @PutMapping
    public ResponseEntity<ApiResponse<Student>> updateProfile(@RequestBody Student profileUpdate, 
            Authentication authentication) {
        String userId = (String) authentication.getDetails();
        Student updatedStudent = studentProfileService.updateProfile(userId, profileUpdate);
        return ResponseEntity.ok(ApiResponse.success("Profile updated successfully", updatedStudent));
    }
}