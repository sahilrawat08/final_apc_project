package com.university.lms.common.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Document(collection = "submissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submission {
    
    @Id
    private String id;
    
    private String assignmentId;
    
    private String studentId;
    
    private String studentName;
    
    private String content;
    
    private String attachmentUrl;
    
    private Integer grade;
    
    private String feedback;
    
    private SubmissionStatus status = SubmissionStatus.SUBMITTED;
    
    private LocalDateTime submittedAt = LocalDateTime.now();
    
    private LocalDateTime gradedAt;
    
    public enum SubmissionStatus {
        SUBMITTED, GRADED, LATE_SUBMISSION
    }
}