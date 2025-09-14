package com.university.lms.controller;

import com.university.lms.model.Question;
import com.university.lms.model.Test;
import com.university.lms.service.QuestionService;
import com.university.lms.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController {
    
    @Autowired
    private QuestionService questionService;
    
    @Autowired
    private TestService testService;
    
    @GetMapping
    public String listQuestions(Model model) {
        List<Question> questions = questionService.getAllQuestions();
        model.addAttribute("questions", questions);
        return "questions/list";
    }
    
    @GetMapping("/new")
    public String showQuestionForm(Model model) {
        model.addAttribute("question", new Question());
        List<Test> tests = testService.getAllTests();
        model.addAttribute("tests", tests);
        return "questions/form";
    }
    
    @PostMapping("/save")
    public String saveQuestion(@ModelAttribute Question question) {
        questionService.saveQuestion(question);
        return "redirect:/questions";
    }
    
    @GetMapping("/edit/{id}")
    public String editQuestion(@PathVariable Long id, Model model) {
        Question question = questionService.getQuestionById(id).orElse(null);
        model.addAttribute("question", question);
        List<Test> tests = testService.getAllTests();
        model.addAttribute("tests", tests);
        return "questions/form";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return "redirect:/questions";
    }
}
