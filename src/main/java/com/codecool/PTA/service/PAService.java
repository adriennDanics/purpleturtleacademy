package com.codecool.PTA.service;

import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.PA;
import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.repository.PARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PAService {

    @Autowired
    private PARepository paRepository;

    public List<PA> findPaTemplatesByCourseNameAndLevelName(CourseType courseType, Level level) {
        return paRepository.getAllByCourseTypeAndLevelAndIsTemplate(courseType, level, true);
    }

    public void save(PA pa) {
        paRepository.save(pa);
    }

    public PA findById(Long id) { return paRepository.findOne(id); }

}
