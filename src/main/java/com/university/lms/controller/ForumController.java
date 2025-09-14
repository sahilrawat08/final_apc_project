package com.university.lms.controller;

import com.university.lms.model.ForumPost;
import com.university.lms.model.Course;
import com.university.lms.model.Student;
import com.university.lms.service.ForumPostService;
import com.university.lms.service.CourseService;
import com.university.lms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/forum")
public class ForumController {
    
    @Autowired
    private ForumPostService forumPostService;
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private StudentService studentService;
    
    @GetMapping
    public String listForumPosts(Model model) {
        List<ForumPost> forumPosts = forumPostService.getAllForumPosts();
        model.addAttribute("forumPosts", forumPosts);
        return "forum/list";
    }
    
    @GetMapping("/course/{courseId}")
    public String listForumPostsByCourse(@PathVariable Long courseId, Model model) {
        List<ForumPost> forumPosts = forumPostService.getForumPostsByCourse(courseId);
        Course course = courseService.getCourseById(courseId).orElse(null);
        model.addAttribute("forumPosts", forumPosts);
        model.addAttribute("course", course);
        return "forum/course-posts";
    }
    
    @GetMapping("/new")
    public String showForumPostForm(Model model) {
        model.addAttribute("forumPost", new ForumPost());
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "forum/form";
    }
    
    @PostMapping("/save")
    public String saveForumPost(@ModelAttribute ForumPost forumPost) {
        forumPostService.saveForumPost(forumPost);
        return "redirect:/forum";
    }
    
    @GetMapping("/edit/{id}")
    public String editForumPost(@PathVariable Long id, Model model) {
        ForumPost forumPost = forumPostService.getForumPostById(id).orElse(null);
        model.addAttribute("forumPost", forumPost);
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "forum/form";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteForumPost(@PathVariable Long id) {
        forumPostService.deleteForumPost(id);
        return "redirect:/forum";
    }
}
