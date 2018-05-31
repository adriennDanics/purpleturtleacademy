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

    public QuizQuestion(){ super();}

    public QuizQuestion(String question, Level level, CourseType courseType, Map<String, Boolean> answers) {
        this.answers = answers;
    }

    public Map<String, Boolean> getAnswers() {
        return answers;
    }
}
