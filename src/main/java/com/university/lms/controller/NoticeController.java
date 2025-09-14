package com.university.lms.controller;

import com.university.lms.model.Notice;
import com.university.lms.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notices")
public class NoticeController {
    
    @Autowired
    private NoticeService noticeService;
    
    @GetMapping
    public String listNotices(Model model) {
        List<Notice> notices = noticeService.getAllNotices();
        model.addAttribute("notices", notices);
        return "notices/list";
    }
    
    @GetMapping("/new")
    public String showNoticeForm(Model model) {
        model.addAttribute("notice", new Notice());
        return "notices/form";
    }
    
    @PostMapping("/save")
    public String saveNotice(@ModelAttribute Notice notice) {
        noticeService.saveNotice(notice);
        return "redirect:/notices";
    }
    
    @GetMapping("/edit/{id}")
    public String editNotice(@PathVariable Long id, Model model) {
        Notice notice = noticeService.getNoticeById(id).orElse(null);
        model.addAttribute("notice", notice);
        return "notices/form";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return "redirect:/notices";
    }
}
