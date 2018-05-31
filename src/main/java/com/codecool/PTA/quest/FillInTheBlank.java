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

//    @ElementCollection
    @OneToMany
    private Set<FillInAnswer> answers = new HashSet<>();

    public FillInTheBlank() {
    }

    public FillInTheBlank(String assignmentTitle,
                          String question,
                          Level level,
                          CourseType courseType,
                          Set<FillInAnswer> answers) {
        super(assignmentTitle);
        this.question = question;
        this.level = level;
        this.courseType = courseType;
        this.answers = answers;
    }
}
