package com.codecool.PTA.quest;

import com.codecool.PTA.user.Level;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class QuizQuestion extends Assignment {

    @Column(nullable = false)
    String question;

    @Column(nullable = false)
    Level level;

    @Column(nullable = false)
    CourseType courseType;

    @ElementCollection
    @MapKeyColumn(name = "answer")
    private Map<String, Boolean> answers = new HashMap<>();

    public QuizQuestion(){}

    public QuizQuestion(String assignmentTitle, String question, Level level, CourseType courseType, Map<String, Boolean> answers) {
        super(assignmentTitle);
        this.question = question;
        this.level = level;
        this.courseType = courseType;
        this.answers = answers;
    }
}
