package com.codecool.PTA.service;

import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.Kata;
import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.repository.KataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KataService {

    @Autowired
    private KataRepository kataRepository;

    public List<Kata> findKatasByCourseNameAndLevelName(CourseType courseType, Level level) {
        return kataRepository.getByCourseTypeAndLevel(courseType, level);
    }
}
