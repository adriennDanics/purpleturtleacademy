package com.codecool.PTA.repository;

import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.Kata;
import com.codecool.PTA.model.user.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KataRepository extends JpaRepository<Kata, Long> {

    List<Kata> getAllByCourseTypeAndLevelAndIsTemplate(CourseType courseType, Level level, boolean isTemplate);

}
