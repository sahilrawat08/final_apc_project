package com.university.lms.common.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Document(collection = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    
    @Id
    private String id;
    
    private String title;
    
    private String description;
    
    private String instructorId;
    
    private String instructorName;
    
    private List<String> enrolledStudents = new ArrayList<>();
    
    private int maxStudents = 50;
    
    private CourseStatus status = CourseStatus.ACTIVE;
    
    private LocalDateTime createdAt = LocalDateTime.now();
    
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    private LocalDateTime startDate;
    
    private LocalDateTime endDate;
    
    public enum CourseStatus {
        ACTIVE, INACTIVE, COMPLETED
    }
}