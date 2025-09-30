package com.university.lms.admin.service;

import com.university.lms.common.model.*;
import com.university.lms.admin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class DataInitializationService implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private AssignmentRepository assignmentRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        initializeData();
    }
    
    private void initializeData() {
        // Check if data already exists
        if (userRepository.count() > 0) {
            return;
        }
        
        // Create admin user
        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@university.edu");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setFirstName("System");
        admin.setLastName("Administrator");
        admin.setRole(User.Role.ADMIN);
        admin.setCreatedAt(LocalDateTime.now());
        admin.setUpdatedAt(LocalDateTime.now());
        admin.setEnabled(true);
        User savedAdmin = userRepository.save(admin);
        
        // Create sample student users
        User student1User = new User();
        student1User.setUsername("STU001");
        student1User.setEmail("john.doe@student.edu");
        student1User.setPassword(passwordEncoder.encode("password123"));
        student1User.setFirstName("John");
        student1User.setLastName("Doe");
        student1User.setRole(User.Role.STUDENT);
        student1User.setCreatedAt(LocalDateTime.now());
        student1User.setUpdatedAt(LocalDateTime.now());
        student1User.setEnabled(true);
        User savedStudent1User = userRepository.save(student1User);
        
        User student2User = new User();
        student2User.setUsername("STU002");
        student2User.setEmail("jane.smith@student.edu");
        student2User.setPassword(passwordEncoder.encode("password123"));
        student2User.setFirstName("Jane");
        student2User.setLastName("Smith");
        student2User.setRole(User.Role.STUDENT);
        student2User.setCreatedAt(LocalDateTime.now());
        student2User.setUpdatedAt(LocalDateTime.now());
        student2User.setEnabled(true);
        User savedStudent2User = userRepository.save(student2User);
        
        // Create sample students
        Student student1 = new Student();
        student1.setUserId(savedStudent1User.getId());
        student1.setStudentId("STU001");
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setEmail("john.doe@student.edu");
        student1.setDepartment("Computer Science");
        student1.setPhoneNumber("+1-555-0101");
        student1.setEnrolledCourses(new ArrayList<>());
        student1.setCreatedAt(LocalDateTime.now());
        student1.setUpdatedAt(LocalDateTime.now());
        studentRepository.save(student1);
        
        Student student2 = new Student();
        student2.setUserId(savedStudent2User.getId());
        student2.setStudentId("STU002");
        student2.setFirstName("Jane");
        student2.setLastName("Smith");
        student2.setEmail("jane.smith@student.edu");
        student2.setDepartment("Mathematics");
        student2.setPhoneNumber("+1-555-0102");
        student2.setEnrolledCourses(new ArrayList<>());
        student2.setCreatedAt(LocalDateTime.now());
        student2.setUpdatedAt(LocalDateTime.now());
        studentRepository.save(student2);
        
        // Create sample courses
        Course course1 = new Course();
        course1.setTitle("Introduction to Programming");
        course1.setDescription("Learn the fundamentals of programming using Java");
        course1.setInstructorId(savedAdmin.getId());
        course1.setInstructorName("Dr. Johnson");
        course1.setEnrolledStudents(new ArrayList<>());
        course1.setMaxStudents(30);
        course1.setStatus(Course.CourseStatus.ACTIVE);
        course1.setCreatedAt(LocalDateTime.now());
        course1.setUpdatedAt(LocalDateTime.now());
        course1.setStartDate(LocalDateTime.of(2024, 1, 15, 0, 0));
        course1.setEndDate(LocalDateTime.of(2024, 5, 15, 0, 0));
        Course savedCourse1 = courseRepository.save(course1);
        
        Course course2 = new Course();
        course2.setTitle("Data Structures and Algorithms");
        course2.setDescription("Advanced programming concepts and algorithm design");
        course2.setInstructorId(savedAdmin.getId());
        course2.setInstructorName("Dr. Wilson");
        course2.setEnrolledStudents(new ArrayList<>());
        course2.setMaxStudents(25);
        course2.setStatus(Course.CourseStatus.ACTIVE);
        course2.setCreatedAt(LocalDateTime.now());
        course2.setUpdatedAt(LocalDateTime.now());
        course2.setStartDate(LocalDateTime.of(2024, 1, 15, 0, 0));
        course2.setEndDate(LocalDateTime.of(2024, 5, 15, 0, 0));
        Course savedCourse2 = courseRepository.save(course2);
        
        Course course3 = new Course();
        course3.setTitle("Calculus I");
        course3.setDescription("Introduction to differential and integral calculus");
        course3.setInstructorId(savedAdmin.getId());
        course3.setInstructorName("Dr. Brown");
        course3.setEnrolledStudents(new ArrayList<>());
        course3.setMaxStudents(40);
        course3.setStatus(Course.CourseStatus.ACTIVE);
        course3.setCreatedAt(LocalDateTime.now());
        course3.setUpdatedAt(LocalDateTime.now());
        course3.setStartDate(LocalDateTime.of(2024, 1, 15, 0, 0));
        course3.setEndDate(LocalDateTime.of(2024, 5, 15, 0, 0));
        courseRepository.save(course3);
        
        // Create sample assignments
        Assignment assignment1 = new Assignment();
        assignment1.setCourseId(savedCourse1.getId());
        assignment1.setTitle("Hello World Program");
        assignment1.setDescription("Write your first Java program that prints 'Hello, World!' to the console");
        assignment1.setDueDate(LocalDateTime.of(2024, 2, 1, 23, 59));
        assignment1.setMaxScore(100);
        assignment1.setStatus(Assignment.AssignmentStatus.ACTIVE);
        assignment1.setCreatedAt(LocalDateTime.now());
        assignment1.setUpdatedAt(LocalDateTime.now());
        assignmentRepository.save(assignment1);
        
        Assignment assignment2 = new Assignment();
        assignment2.setCourseId(savedCourse2.getId());
        assignment2.setTitle("Binary Search Implementation");
        assignment2.setDescription("Implement binary search algorithm in Java");
        assignment2.setDueDate(LocalDateTime.of(2024, 2, 15, 23, 59));
        assignment2.setMaxScore(100);
        assignment2.setStatus(Assignment.AssignmentStatus.ACTIVE);
        assignment2.setCreatedAt(LocalDateTime.now());
        assignment2.setUpdatedAt(LocalDateTime.now());
        assignmentRepository.save(assignment2);
        
        System.out.println("Sample data initialized successfully!");
    }
}