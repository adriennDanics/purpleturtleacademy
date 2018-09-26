package com.codecool.PTA.service;

import com.codecool.PTA.model.quest.PASolution;
import com.codecool.PTA.repository.PASolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PASolutionService {

    @Autowired
    private PASolutionRepository paSolutionRepository;

    public PASolution findById(Long id) {
        return paSolutionRepository.getOne(id);
    }

    public void save(PASolution paSolution) {
        paSolutionRepository.save(paSolution);
    }

}
