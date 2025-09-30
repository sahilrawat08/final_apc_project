package com.university.lms.admin.repository;

import com.university.lms.common.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);
    
    List<User> findByRole(User.Role role);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}