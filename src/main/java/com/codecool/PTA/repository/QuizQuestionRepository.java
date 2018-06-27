package com.codecool.PTA.repository;

import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.QuizQuestion;
import com.codecool.PTA.model.user.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {

    List<QuizQuestion> getByCourseTypeAndLevel(CourseType courseType, Level level);

}

