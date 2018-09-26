package com.codecool.PTA.repository;

import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.FillInTheBlank;
import com.codecool.PTA.model.user.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FillInTheBlankRepository extends JpaRepository<FillInTheBlank, Long> {

    List<FillInTheBlank> getByCourseTypeAndLevel(CourseType courseType, Level level);
}
