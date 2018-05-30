package com.codecool.PTA.quest;

import com.codecool.PTA.user.Level;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class QuizQuestion extends Assignment {

    @Column(nullable = false)
    String question;

    @Enumerated(EnumType.STRING)
    Level level;

    @Enumerated(EnumType.STRING)
    CourseType courseType;

    @ElementCollection
    @MapKeyColumn(name = "answer")
    private Map<String, Boolean> answers = new HashMap<>();

    public QuizQuestion(){ super();}

    public QuizQuestion(String assignmentTitle, String question, Level level, CourseType courseType, Map<String, Boolean> answers) {
        super(assignmentTitle);
        this.question = question;
        this.level = level;
        this.courseType = courseType;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public Level getLevel() {
        return level;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public Map<String, Boolean> getAnswers() {
        return answers;
    }
}
