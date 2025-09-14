package com.university.lms.repository;

import com.university.lms.model.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumPostRepository extends JpaRepository<ForumPost, Long> {
    List<ForumPost> findByCourseIdOrderByCreatedAtDesc(Long courseId);
}
