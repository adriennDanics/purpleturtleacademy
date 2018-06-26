package com.codecool.PTA.service;

import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.PA;
import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.repository.PARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaService {

    @Autowired
    private PARepository paRepository;

    public List<PA> findPasByCourseNameAndLevelName(CourseType courseType, Level level) {
        return paRepository.getByCourseTypeAndLevel(courseType, level);
    }
}
