package com.codecool.PTA.service;

import com.codecool.PTA.model.quest.QuizQuestion;
import com.codecool.PTA.repository.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizQuestionService {

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;


    public void save(QuizQuestion question) {
        quizQuestionRepository.save(question);
    }
    public QuizQuestion findById(Long id) { return quizQuestionRepository.findOne(id); }
}
