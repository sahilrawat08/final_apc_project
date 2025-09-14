package com.university.lms.service;

import com.university.lms.model.Grade;
import com.university.lms.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {
    
    @Autowired
    private GradeRepository gradeRepository;
    
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }
    
    public Optional<Grade> getGradeById(Long id) {
        return gradeRepository.findById(id);
    }
    
    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }
    
    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }
    
    public List<Grade> getGradesByStudent(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }
    
    public List<Grade> getGradesByCourse(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }
    
    public List<Grade> getGradesByStudentAndCourse(Long studentId, Long courseId) {
        return gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
    }
}
