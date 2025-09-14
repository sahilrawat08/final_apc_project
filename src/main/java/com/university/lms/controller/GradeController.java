package com.university.lms.controller;

import com.university.lms.model.Grade;
import com.university.lms.model.Student;
import com.university.lms.model.Course;
import com.university.lms.service.GradeService;
import com.university.lms.service.StudentService;
import com.university.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/grades")
public class GradeController {
    
    @Autowired
    private GradeService gradeService;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private CourseService courseService;
    
    @GetMapping
    public String listGrades(Model model) {
        List<Grade> grades = gradeService.getAllGrades();
        model.addAttribute("grades", grades);
        return "grades/list";
    }
    
    @GetMapping("/new")
    public String showGradeForm(Model model) {
        model.addAttribute("grade", new Grade());
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "grades/form";
    }
    
    @PostMapping("/save")
    public String saveGrade(@ModelAttribute Grade grade) {
        gradeService.saveGrade(grade);
        return "redirect:/grades";
    }
    
    @GetMapping("/edit/{id}")
    public String editGrade(@PathVariable Long id, Model model) {
        Grade grade = gradeService.getGradeById(id).orElse(null);
        model.addAttribute("grade", grade);
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "grades/form";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return "redirect:/grades";
    }
}
