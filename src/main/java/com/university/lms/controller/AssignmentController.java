package com.university.lms.controller;

import com.university.lms.model.Assignment;
import com.university.lms.model.Course;
import com.university.lms.service.AssignmentService;
import com.university.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/assignments")
public class AssignmentController {
    
    @Autowired
    private AssignmentService assignmentService;
    
    @Autowired
    private CourseService courseService;
    
    @GetMapping
    public String listAssignments(Model model) {
        List<Assignment> assignments = assignmentService.getAllAssignments();
        model.addAttribute("assignments", assignments);
        return "assignments/list";
    }
    
    @GetMapping("/new")
    public String showAssignmentForm(Model model) {
        model.addAttribute("assignment", new Assignment());
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "assignments/form";
    }
    
    @PostMapping("/save")
    public String saveAssignment(@ModelAttribute Assignment assignment) {
        assignmentService.saveAssignment(assignment);
        return "redirect:/assignments";
    }
    
    @GetMapping("/edit/{id}")
    public String editAssignment(@PathVariable Long id, Model model) {
        Assignment assignment = assignmentService.getAssignmentById(id).orElse(null);
        model.addAttribute("assignment", assignment);
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "assignments/form";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return "redirect:/assignments";
    }
}
