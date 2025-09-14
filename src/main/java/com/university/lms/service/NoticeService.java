package com.university.lms.service;

import com.university.lms.model.Notice;
import com.university.lms.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {
    
    @Autowired
    private NoticeRepository noticeRepository;
    
    public List<Notice> getAllNotices() {
        return noticeRepository.findAllByOrderByCreatedAtDesc();
    }
    
    public Optional<Notice> getNoticeById(Long id) {
        return noticeRepository.findById(id);
    }
    
    public Notice saveNotice(Notice notice) {
        return noticeRepository.save(notice);
    }
    
    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }
}
