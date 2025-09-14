package com.university.lms.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "roll_number", unique = true, nullable = false)
    private String rollNumber;
    
    @Column(nullable = false)
    private String department;
    
    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Grade> grades = new ArrayList<>();
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ForumPost> forumPosts = new ArrayList<>();
}
