package com.codecool.PTA.service;

import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course findById(Long id) {
        return courseRepository.findOne(id);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public void save(Course course) {
        courseRepository.save(course);
    }

}
