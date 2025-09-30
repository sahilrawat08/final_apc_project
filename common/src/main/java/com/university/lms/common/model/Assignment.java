package com.university.lms.common.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Document(collection = "assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {
    
    @Id
    private String id;
    
    private String courseId;
    
    private String title;
    
    private String description;
    
    private LocalDateTime dueDate;
    
    private int maxScore = 100;
    
    private AssignmentStatus status = AssignmentStatus.ACTIVE;
    
    private LocalDateTime createdAt = LocalDateTime.now();
    
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    public enum AssignmentStatus {
        ACTIVE, INACTIVE, COMPLETED
    }
}