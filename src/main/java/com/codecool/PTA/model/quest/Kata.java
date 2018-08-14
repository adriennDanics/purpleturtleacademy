package com.codecool.PTA.model.quest;

import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.user.Level;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Kata extends Assignment {

    @Column
    public boolean isTemplate;

    protected Kata() {
        super();
    }

    public Kata(Level level, CourseType courseType, String assignmentTitle, String question, boolean isTemplate) {
        super(level, courseType, assignmentTitle, question);
        this.isTemplate = isTemplate;
    }

}
