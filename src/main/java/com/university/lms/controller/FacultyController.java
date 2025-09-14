package com.university.lms.controller;

import com.university.lms.model.Faculty;
import com.university.lms.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/faculty")
public class FacultyController {
    
    @Autowired
    private FacultyService facultyService;
    
    @GetMapping
    public String listFaculty(Model model) {
        List<Faculty> faculty = facultyService.getAllFaculty();
        model.addAttribute("faculty", faculty);
        return "faculty/list";
    }
    
    @GetMapping("/new")
    public String showFacultyForm(Model model) {
        model.addAttribute("faculty", new Faculty());
        return "faculty/form";
    }
    
    @PostMapping("/save")
    public String saveFaculty(@ModelAttribute Faculty faculty) {
        facultyService.saveFaculty(faculty);
        return "redirect:/faculty";
    }
    
    @GetMapping("/edit/{id}")
    public String editFaculty(@PathVariable Long id, Model model) {
        Faculty faculty = facultyService.getFacultyById(id).orElse(null);
        model.addAttribute("faculty", faculty);
        return "faculty/form";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return "redirect:/faculty";
    }
}
