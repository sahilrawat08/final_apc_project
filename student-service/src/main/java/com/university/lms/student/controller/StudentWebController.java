package com.university.lms.student.controller;

import com.university.lms.student.service.CourseService;
import com.university.lms.student.service.AssignmentService;
import com.university.lms.student.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentWebController {
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private AssignmentService assignmentService;
    
    @Autowired
    private SubmissionService submissionService;
    
    @GetMapping("/login")
    public String loginPage() {
        return "student/login";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("availableCoursesCount", courseService.getAllActiveCourses().size());
        model.addAttribute("activeAssignmentsCount", assignmentService.getActiveAssignments().size());
        return "student/dashboard";
    }
    
    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("courses", courseService.getAllActiveCourses());
        return "student/courses";
    }
    
    @GetMapping("/assignments")
    public String assignments(Model model) {
        model.addAttribute("assignments", assignmentService.getActiveAssignments());
        return "student/assignments";
    }
    
    @GetMapping("/profile")
    public String profile() {
        return "student/profile";
    }
}