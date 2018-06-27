package com.codecool.PTA.service;

import com.codecool.PTA.model.quest.FillInTheBlank;
import com.codecool.PTA.repository.FillInTheBlankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FillInTheBlankService {

    @Autowired
    private FillInTheBlankRepository fillInTheBlankRepository;

    public FillInTheBlank findById(Long id) {
        return fillInTheBlankRepository.findOne(id);
    }

    public void save(FillInTheBlank fillInTheBlank) {
        fillInTheBlankRepository.save(fillInTheBlank);
    }
}
