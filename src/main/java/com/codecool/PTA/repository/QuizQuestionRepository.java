package com.codecool.PTA.repository;

import com.codecool.PTA.model.quest.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {
}
