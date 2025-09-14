package com.university.lms.controller;

import com.university.lms.model.Course;
import com.university.lms.model.Faculty;
import com.university.lms.service.CourseService;
import com.university.lms.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private FacultyService facultyService;
    
    @GetMapping
    public String listCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses/list";
    }
    
    @GetMapping("/new")
    public String showCourseForm(Model model) {
        model.addAttribute("course", new Course());
        List<Faculty> faculty = facultyService.getAllFaculty();
        model.addAttribute("faculty", faculty);
        return "courses/form";
    }
    
    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }
    
    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id).orElse(null);
        model.addAttribute("course", course);
        List<Faculty> faculty = facultyService.getAllFaculty();
        model.addAttribute("faculty", faculty);
        return "courses/form";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
}
