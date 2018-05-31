package com.codecool.PTA.quest;

import com.codecool.PTA.user.Level;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class QuizQuestion extends Assignment {

    @ElementCollection
    @MapKeyColumn(name = "answer")
    private Map<String, Boolean> answers = new HashMap<>();

    protected QuizQuestion() {
        super();
    }

    public QuizQuestion(String question, String assignmentTitle, Level level, CourseType courseType, Map<String, Boolean> answers) {
        super(level, courseType, assignmentTitle, question);
        this.answers = answers;
    }

    public Map<String, Boolean> getAnswers() {
        return answers;
    }
}
