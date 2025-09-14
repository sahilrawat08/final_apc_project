package com.university.lms.controller;

import com.university.lms.model.Notice;
import com.university.lms.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {
    
    @Autowired
    private NoticeService noticeService;
    
    @GetMapping("/")
    public String dashboard(Model model) {
        List<Notice> notices = noticeService.getAllNotices();
        model.addAttribute("notices", notices);
        return "dashboard";
    }
}
