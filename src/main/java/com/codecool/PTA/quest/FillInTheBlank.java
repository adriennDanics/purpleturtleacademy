package com.codecool.PTA.quest;

import com.codecool.PTA.user.Level;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FillInTheBlank extends Assignment {

    @Column(nullable = false)
    private String question;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Enumerated(EnumType.STRING)
    private CourseType courseType;

    @OneToMany(mappedBy = "question")
    private Set<FillInAnswer> answers = new HashSet<>();

    public FillInTheBlank() {
    }

    public FillInTheBlank(Level level,
                          CourseType courseType,
                          String assignmentTitle,
                          String question,
                          String question1,
                          Level level1,
                          CourseType courseType1,
                          Set<FillInAnswer> answers) {
        super(level, courseType, assignmentTitle, question);
        this.question = question1;
        this.level = level1;
        this.courseType = courseType1;
        this.answers = answers;
    }
}
