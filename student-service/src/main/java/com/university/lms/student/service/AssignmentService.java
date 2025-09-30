package com.university.lms.student.service;

import com.university.lms.common.model.Assignment;
import com.university.lms.common.exception.ResourceNotFoundException;
import com.university.lms.student.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {
    
    @Autowired
    private AssignmentRepository assignmentRepository;
    
    public Assignment getAssignmentById(String id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + id));
    }
    
    public List<Assignment> getAssignmentsByCourse(String courseId) {
        return assignmentRepository.findByCourseId(courseId);
    }
    
    public List<Assignment> getActiveAssignments() {
        return assignmentRepository.findByStatus(Assignment.AssignmentStatus.ACTIVE);
    }
}