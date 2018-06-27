package com.codecool.PTA.service;

import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.QuizQuestion;
import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.repository.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizQuestionService {

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;


    public void save(QuizQuestion question) {
        quizQuestionRepository.save(question);
    }
    public QuizQuestion findById(Long id) { return quizQuestionRepository.findOne(id); }

    public List<QuizQuestion> getAll() { return quizQuestionRepository.findAll(); }

    public List<QuizQuestion> findFillInTheBlanksByCourseTypeAndLevel(CourseType courseType, Level level) {
        return quizQuestionRepository.getByCourseTypeAndLevel(courseType, level);
    }
}
