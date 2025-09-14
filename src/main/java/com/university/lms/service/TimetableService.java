package com.university.lms.service;

import com.university.lms.model.Timetable;
import com.university.lms.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimetableService {
    
    @Autowired
    private TimetableRepository timetableRepository;
    
    public List<Timetable> getAllTimetables() {
        return timetableRepository.findAll();
    }
    
    public Optional<Timetable> getTimetableById(Long id) {
        return timetableRepository.findById(id);
    }
    
    public Timetable saveTimetable(Timetable timetable) {
        return timetableRepository.save(timetable);
    }
    
    public void deleteTimetable(Long id) {
        timetableRepository.deleteById(id);
    }
    
    public List<Timetable> getTimetablesByCourse(Long courseId) {
        return timetableRepository.findByCourseId(courseId);
    }
    
    public List<Timetable> getTimetablesByDay(String day) {
        return timetableRepository.findByDay(day);
    }
}
