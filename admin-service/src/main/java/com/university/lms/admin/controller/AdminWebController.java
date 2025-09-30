package com.university.lms.admin.controller;

import com.university.lms.admin.service.CourseService;
import com.university.lms.admin.service.StudentService;
import com.university.lms.admin.service.AssignmentService;
import com.university.lms.admin.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminWebController {
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private AssignmentService assignmentService;
    
    @Autowired
    private SubmissionService submissionService;
    
    @GetMapping("/login")
    public String loginPage() {
        return "admin/login";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("coursesCount", courseService.getAllCourses().size());
        model.addAttribute("studentsCount", studentService.getAllStudents().size());
        model.addAttribute("assignmentsCount", assignmentService.getAllAssignments().size());
        model.addAttribute("submissionsCount", submissionService.getAllSubmissions().size());
        return "admin/dashboard";
    }
    
    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "admin/courses";
    }
    
    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "admin/students";
    }
    
    @GetMapping("/assignments")
    public String assignments(Model model) {
        model.addAttribute("assignments", assignmentService.getAllAssignments());
        return "admin/assignments";
    }
    
    @GetMapping("/submissions")
    public String submissions(Model model) {
        model.addAttribute("submissions", submissionService.getAllSubmissions());
        return "admin/submissions";
    }
}