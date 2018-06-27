package com.codecool.PTA.repository;

import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.PA;
import com.codecool.PTA.model.user.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PARepository extends JpaRepository<PA, Long> {

    List<PA> getByCourseTypeAndLevelAndTemplate(CourseType courseType, Level level, boolean isTemplate);

}
