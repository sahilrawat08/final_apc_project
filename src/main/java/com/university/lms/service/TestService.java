package com.university.lms.service;

import com.university.lms.model.Test;
import com.university.lms.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    
    @Autowired
    private TestRepository testRepository;
    
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }
    
    public Optional<Test> getTestById(Long id) {
        return testRepository.findById(id);
    }
    
    public Test saveTest(Test test) {
        return testRepository.save(test);
    }
    
    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
    
    public List<Test> getTestsByCourse(Long courseId) {
        return testRepository.findByCourseId(courseId);
    }
}
