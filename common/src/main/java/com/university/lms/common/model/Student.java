package com.university.lms.common.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Document(collection = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    
    @Id
    private String id;
    
    private String userId; // Reference to User document
    
    @Indexed(unique = true)
    private String studentId;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String department;
    
    private String phoneNumber;
    
    private List<String> enrolledCourses = new ArrayList<>();
    
    private LocalDateTime createdAt = LocalDateTime.now();
    
    private LocalDateTime updatedAt = LocalDateTime.now();
}