package com.university.lms.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;
    
    @Column(name = "question_text", columnDefinition = "TEXT", nullable = false)
    private String questionText;
    
    @Column(columnDefinition = "TEXT")
    private String options;
    
    @Column(name = "correct_answer", nullable = false)
    private String correctAnswer;
}
