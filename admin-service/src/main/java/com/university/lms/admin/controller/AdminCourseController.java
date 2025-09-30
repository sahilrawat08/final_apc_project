package com.university.lms.admin.controller;

import com.university.lms.common.dto.ApiResponse;
import com.university.lms.common.model.Course;
import com.university.lms.admin.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/courses")
@CrossOrigin(origins = "*")
public class AdminCourseController {
    
    @Autowired
    private CourseService courseService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(ApiResponse.success(courses));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable String id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(ApiResponse.success(course));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<Course>> createCourse(@Valid @RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return ResponseEntity.ok(ApiResponse.success("Course created successfully", createdCourse));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@PathVariable String id, 
            @Valid @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(id, course);
        return ResponseEntity.ok(ApiResponse.success("Course updated successfully", updatedCourse));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok(ApiResponse.success("Course deleted successfully", null));
    }
    
    @PostMapping("/{courseId}/enroll/{studentId}")
    public ResponseEntity<ApiResponse<Course>> enrollStudent(@PathVariable String courseId, 
            @PathVariable String studentId) {
        Course course = courseService.enrollStudent(courseId, studentId);
        return ResponseEntity.ok(ApiResponse.success("Student enrolled successfully", course));
    }
    
    @DeleteMapping("/{courseId}/enroll/{studentId}")
    public ResponseEntity<ApiResponse<Course>> unenrollStudent(@PathVariable String courseId, 
            @PathVariable String studentId) {
        Course course = courseService.unenrollStudent(courseId, studentId);
        return ResponseEntity.ok(ApiResponse.success("Student unenrolled successfully", course));
    }
}