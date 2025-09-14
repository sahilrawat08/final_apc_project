package com.university.lms.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "forum_posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForumPost {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
