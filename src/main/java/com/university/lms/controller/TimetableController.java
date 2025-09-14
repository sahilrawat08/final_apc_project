package com.university.lms.controller;

import com.university.lms.model.Timetable;
import com.university.lms.model.Course;
import com.university.lms.service.TimetableService;
import com.university.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/timetable")
public class TimetableController {
    
    @Autowired
    private TimetableService timetableService;
    
    @Autowired
    private CourseService courseService;
    
    @GetMapping
    public String listTimetables(Model model) {
        List<Timetable> timetables = timetableService.getAllTimetables();
        model.addAttribute("timetables", timetables);
        return "timetable/list";
    }
    
    @GetMapping("/new")
    public String showTimetableForm(Model model) {
        model.addAttribute("timetable", new Timetable());
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "timetable/form";
    }
    
    @PostMapping("/save")
    public String saveTimetable(@ModelAttribute Timetable timetable) {
        timetableService.saveTimetable(timetable);
        return "redirect:/timetable";
    }
    
    @GetMapping("/edit/{id}")
    public String editTimetable(@PathVariable Long id, Model model) {
        Timetable timetable = timetableService.getTimetableById(id).orElse(null);
        model.addAttribute("timetable", timetable);
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "timetable/form";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteTimetable(@PathVariable Long id) {
        timetableService.deleteTimetable(id);
        return "redirect:/timetable";
    }
}
