package com.codecool.PTA.service;

import com.codecool.PTA.model.quest.Kata;
import com.codecool.PTA.model.quest.KataSolution;
import com.codecool.PTA.repository.KataSolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KataSolutionService {

    @Autowired
    private KataSolutionRepository kataSolutionRepository;

    public KataSolution findById(Long id) { return kataSolutionRepository.getOne(id); }

    public void save(KataSolution kataSolution) {
        kataSolutionRepository.save(kataSolution);
    }

}
