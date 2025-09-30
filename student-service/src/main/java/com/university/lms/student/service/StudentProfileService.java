package com.university.lms.student.service;

import com.university.lms.common.model.Student;
import com.university.lms.common.model.User;
import com.university.lms.common.exception.ResourceNotFoundException;
import com.university.lms.student.repository.StudentRepository;
import com.university.lms.student.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentProfileService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Student getStudentByUserId(String userId) {
        return studentRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found for user: " + userId));
    }
    
    public Student updateProfile(String userId, Student profileUpdate) {
        Student student = getStudentByUserId(userId);
        
        student.setFirstName(profileUpdate.getFirstName());
        student.setLastName(profileUpdate.getLastName());
        student.setPhoneNumber(profileUpdate.getPhoneNumber());
        student.setDepartment(profileUpdate.getDepartment());
        student.setUpdatedAt(LocalDateTime.now());
        
        // Update user details if email changed
        if (!student.getEmail().equals(profileUpdate.getEmail())) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            
            user.setEmail(profileUpdate.getEmail());
            user.setFirstName(profileUpdate.getFirstName());
            user.setLastName(profileUpdate.getLastName());
            user.setUpdatedAt(LocalDateTime.now());
            
            userRepository.save(user);
            student.setEmail(profileUpdate.getEmail());
        }
        
        return studentRepository.save(student);
    }
}