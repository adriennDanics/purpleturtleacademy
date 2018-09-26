package com.codecool.PTA.repository;

import com.codecool.PTA.model.quest.FillInAnswer;
import com.codecool.PTA.model.quest.FillInTheBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FillInAnswerRepository extends JpaRepository<FillInAnswer, Long> {

    List<FillInAnswer> getByQuestion(FillInTheBlank fillInTheBlank);
}
