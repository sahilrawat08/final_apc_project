package com.university.lms.admin.service;

import com.university.lms.common.model.Course;
import com.university.lms.common.exception.ResourceNotFoundException;
import com.university.lms.admin.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
    
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    
    public Course getCourseById(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }
    
    public Course createCourse(Course course) {
        course.setCreatedAt(LocalDateTime.now());
        course.setUpdatedAt(LocalDateTime.now());
        return courseRepository.save(course);
    }
    
    public Course updateCourse(String id, Course courseDetails) {
        Course course = getCourseById(id);
        
        course.setTitle(courseDetails.getTitle());
        course.setDescription(courseDetails.getDescription());
        course.setInstructorId(courseDetails.getInstructorId());
        course.setInstructorName(courseDetails.getInstructorName());
        course.setMaxStudents(courseDetails.getMaxStudents());
        course.setStatus(courseDetails.getStatus());
        course.setStartDate(courseDetails.getStartDate());
        course.setEndDate(courseDetails.getEndDate());
        course.setUpdatedAt(LocalDateTime.now());
        
        return courseRepository.save(course);
    }
    
    public void deleteCourse(String id) {
        Course course = getCourseById(id);
        courseRepository.delete(course);
    }
    
    public Course enrollStudent(String courseId, String studentId) {
        Course course = getCourseById(courseId);
        
        if (course.getEnrolledStudents().contains(studentId)) {
            throw new RuntimeException("Student already enrolled in this course");
        }
        
        if (course.getEnrolledStudents().size() >= course.getMaxStudents()) {
            throw new RuntimeException("Course is full");
        }
        
        course.getEnrolledStudents().add(studentId);
        course.setUpdatedAt(LocalDateTime.now());
        
        return courseRepository.save(course);
    }
    
    public Course unenrollStudent(String courseId, String studentId) {
        Course course = getCourseById(courseId);
        
        course.getEnrolledStudents().remove(studentId);
        course.setUpdatedAt(LocalDateTime.now());
        
        return courseRepository.save(course);
    }
    
    public List<Course> getCoursesByInstructor(String instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }
}