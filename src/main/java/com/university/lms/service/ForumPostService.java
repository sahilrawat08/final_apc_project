package com.university.lms.service;

import com.university.lms.model.ForumPost;
import com.university.lms.repository.ForumPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForumPostService {
    
    @Autowired
    private ForumPostRepository forumPostRepository;
    
    public List<ForumPost> getAllForumPosts() {
        return forumPostRepository.findAll();
    }
    
    public Optional<ForumPost> getForumPostById(Long id) {
        return forumPostRepository.findById(id);
    }
    
    public ForumPost saveForumPost(ForumPost forumPost) {
        return forumPostRepository.save(forumPost);
    }
    
    public void deleteForumPost(Long id) {
        forumPostRepository.deleteById(id);
    }
    
    public List<ForumPost> getForumPostsByCourse(Long courseId) {
        return forumPostRepository.findByCourseIdOrderByCreatedAtDesc(courseId);
    }
}
