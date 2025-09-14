package com.university.lms.controller;

import com.university.lms.model.Test;
import com.university.lms.model.Course;
import com.university.lms.service.TestService;
import com.university.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tests")
public class TestController {
    
    @Autowired
    private TestService testService;
    
    @Autowired
    private CourseService courseService;
    
    @GetMapping
    public String listTests(Model model) {
        List<Test> tests = testService.getAllTests();
        model.addAttribute("tests", tests);
        return "tests/list";
    }
    
    @GetMapping("/new")
    public String showTestForm(Model model) {
        model.addAttribute("test", new Test());
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "tests/form";
    }
    
    @PostMapping("/save")
    public String saveTest(@ModelAttribute Test test) {
        testService.saveTest(test);
        return "redirect:/tests";
    }
    
    @GetMapping("/edit/{id}")
    public String editTest(@PathVariable Long id, Model model) {
        Test test = testService.getTestById(id).orElse(null);
        model.addAttribute("test", test);
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "tests/form";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteTest(@PathVariable Long id) {
        testService.deleteTest(id);
        return "redirect:/tests";
    }
}
