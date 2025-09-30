package com.university.lms.admin.service;

import com.university.lms.common.model.Student;
import com.university.lms.common.model.User;
import com.university.lms.common.exception.ResourceNotFoundException;
import com.university.lms.admin.repository.StudentRepository;
import com.university.lms.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    public Student getStudentById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }
    
    public Student getStudentByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with student ID: " + studentId));
    }
    
    public Student createStudent(Student student) {
        if (studentRepository.existsByStudentId(student.getStudentId())) {
            throw new RuntimeException("Student ID already exists");
        }
        
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        // Create user account for the student
        User user = new User();
        user.setUsername(student.getStudentId());
        user.setEmail(student.getEmail());
        user.setPassword(passwordEncoder.encode("password123")); // Default password
        user.setFirstName(student.getFirstName());
        user.setLastName(student.getLastName());
        user.setRole(User.Role.STUDENT);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        
        User savedUser = userRepository.save(user);
        student.setUserId(savedUser.getId());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        
        return studentRepository.save(student);
    }
    
    public Student updateStudent(String id, Student studentDetails) {
        Student student = getStudentById(id);
        
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setDepartment(studentDetails.getDepartment());
        student.setPhoneNumber(studentDetails.getPhoneNumber());
        student.setUpdatedAt(LocalDateTime.now());
        
        // Update user details if email changed
        if (student.getUserId() != null) {
            User user = userRepository.findById(student.getUserId()).orElse(null);
            if (user != null) {
                user.setEmail(studentDetails.getEmail());
                user.setFirstName(studentDetails.getFirstName());
                user.setLastName(studentDetails.getLastName());
                user.setUpdatedAt(LocalDateTime.now());
                userRepository.save(user);
            }
        }
        
        return studentRepository.save(student);
    }
    
    public void deleteStudent(String id) {
        Student student = getStudentById(id);
        
        // Delete associated user account
        if (student.getUserId() != null) {
            userRepository.deleteById(student.getUserId());
        }
        
        studentRepository.delete(student);
    }
    
    public List<Student> getStudentsByDepartment(String department) {
        return studentRepository.findByDepartment(department);
    }
    
    public List<Student> getStudentsByCourse(String courseId) {
        return studentRepository.findByEnrolledCoursesContaining(courseId);
    }
}