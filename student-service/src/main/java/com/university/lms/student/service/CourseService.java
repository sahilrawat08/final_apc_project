package com.university.lms.student.service;

import com.university.lms.common.model.Course;
import com.university.lms.common.model.Student;
import com.university.lms.common.exception.ResourceNotFoundException;
import com.university.lms.common.exception.BadRequestException;
import com.university.lms.student.repository.CourseRepository;
import com.university.lms.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    public List<Course> getAllActiveCourses() {
        return courseRepository.findByStatus(Course.CourseStatus.ACTIVE);
    }
    
    public Course getCourseById(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }
    
    public List<Course> getEnrolledCourses(String studentId) {
        return courseRepository.findByEnrolledStudentsContaining(studentId);
    }
    
    public Course enrollInCourse(String courseId, String studentId) {
        Course course = getCourseById(courseId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        
        if (course.getEnrolledStudents().contains(studentId)) {
            throw new BadRequestException("Student already enrolled in this course");
        }
        
        if (course.getEnrolledStudents().size() >= course.getMaxStudents()) {
            throw new BadRequestException("Course is full");
        }
        
        if (!course.getStatus().equals(Course.CourseStatus.ACTIVE)) {
            throw new BadRequestException("Course is not active for enrollment");
        }
        
        // Add student to course
        course.getEnrolledStudents().add(studentId);
        course.setUpdatedAt(LocalDateTime.now());
        
        // Add course to student's enrolled courses
        student.getEnrolledCourses().add(courseId);
        student.setUpdatedAt(LocalDateTime.now());
        
        studentRepository.save(student);
        return courseRepository.save(course);
    }
    
    public void unenrollFromCourse(String courseId, String studentId) {
        Course course = getCourseById(courseId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        
        // Remove student from course
        course.getEnrolledStudents().remove(studentId);
        course.setUpdatedAt(LocalDateTime.now());
        
        // Remove course from student's enrolled courses
        student.getEnrolledCourses().remove(courseId);
        student.setUpdatedAt(LocalDateTime.now());
        
        studentRepository.save(student);
        courseRepository.save(course);
    }
}