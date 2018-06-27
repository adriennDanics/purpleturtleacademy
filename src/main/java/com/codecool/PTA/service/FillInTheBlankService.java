package com.codecool.PTA.service;

import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.FillInTheBlank;
import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.repository.FillInTheBlankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillInTheBlankService {

    @Autowired
    private FillInTheBlankRepository fillInTheBlankRepository;

    public FillInTheBlank findById(Long id) {
        return fillInTheBlankRepository.findOne(id);
    }

    public void saveFillInTheBlank(FillInTheBlank fillInTheBlank) {
        fillInTheBlankRepository.save(fillInTheBlank);
    }

    public List<FillInTheBlank> getAll() { return fillInTheBlankRepository.findAll(); }

    public List<FillInTheBlank> findFillInTheBlanksByCourseTypeAndLevel(CourseType courseType, Level level) {
        return fillInTheBlankRepository.getByCourseTypeAndLevel(courseType, level);
    }
}
