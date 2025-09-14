package com.university.lms.service;

import com.university.lms.model.Faculty;
import com.university.lms.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    
    @Autowired
    private FacultyRepository facultyRepository;
    
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }
    
    public Optional<Faculty> getFacultyById(Long id) {
        return facultyRepository.findById(id);
    }
    
    public Faculty saveFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
}
