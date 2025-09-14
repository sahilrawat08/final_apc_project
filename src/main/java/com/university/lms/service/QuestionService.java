package com.university.lms.service;

import com.university.lms.model.Question;
import com.university.lms.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    
    @Autowired
    private QuestionRepository questionRepository;
    
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    
    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }
    
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }
    
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
    
    public List<Question> getQuestionsByTest(Long testId) {
        return questionRepository.findByTestId(testId);
    }
}
