package com.university.lms.student.controller;

import com.university.lms.common.dto.ApiResponse;
import com.university.lms.common.model.Course;
import com.university.lms.student.service.CourseService;
import com.university.lms.student.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/courses")
@CrossOrigin(origins = "*")
public class StudentCourseController {
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private StudentProfileService studentProfileService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllAvailableCourses() {
        List<Course> courses = courseService.getAllActiveCourses();
        return ResponseEntity.ok(ApiResponse.success(courses));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable String id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(ApiResponse.success(course));
    }
    
    @GetMapping("/enrolled")
    public ResponseEntity<ApiResponse<List<Course>>> getEnrolledCourses(Authentication authentication) {
        String userId = (String) authentication.getDetails();
        String studentId = studentProfileService.getStudentByUserId(userId).getId();
        List<Course> courses = courseService.getEnrolledCourses(studentId);
        return ResponseEntity.ok(ApiResponse.success(courses));
    }
    
    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<ApiResponse<Course>> enrollInCourse(@PathVariable String courseId, 
            Authentication authentication) {
        String userId = (String) authentication.getDetails();
        String studentId = studentProfileService.getStudentByUserId(userId).getId();
        Course course = courseService.enrollInCourse(courseId, studentId);
        return ResponseEntity.ok(ApiResponse.success("Enrolled successfully", course));
    }
    
    @DeleteMapping("/{courseId}/unenroll")
    public ResponseEntity<ApiResponse<Void>> unenrollFromCourse(@PathVariable String courseId, 
            Authentication authentication) {
        String userId = (String) authentication.getDetails();
        String studentId = studentProfileService.getStudentByUserId(userId).getId();
        courseService.unenrollFromCourse(courseId, studentId);
        return ResponseEntity.ok(ApiResponse.success("Unenrolled successfully", null));
    }
}