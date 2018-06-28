package com.codecool.PTA.service;

import com.codecool.PTA.model.quest.FillInAnswer;
import com.codecool.PTA.model.quest.FillInTheBlank;
import com.codecool.PTA.repository.FillInAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillInAnswerService {

    @Autowired
    private FillInAnswerRepository fillInAnswerRepository;

    public FillInAnswer findById(Long id) {
        return fillInAnswerRepository.findOne(id);
    }

    public void save(FillInAnswer fillInAnswer) {
        fillInAnswerRepository.save(fillInAnswer);
    }

    public List<FillInAnswer> getAnswersForQuestion(FillInTheBlank fillInTheBlank) {
        return fillInAnswerRepository.getByQuestion(fillInTheBlank);
    }
}
