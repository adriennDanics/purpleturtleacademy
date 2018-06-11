package com.codecool.PTA.model.quest;

import com.codecool.PTA.model.user.Level;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FillInTheBlank extends Assignment {

    @OneToMany(mappedBy = "question")

    private Set<FillInAnswer> answers = new HashSet<>();

    public FillInTheBlank() {
    }

    public FillInTheBlank(Level level, CourseType courseType, String assignmentTitle, String question) {
        super(level, courseType, assignmentTitle, question);
    }

    public Set<FillInAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<FillInAnswer> answers) {
        this.answers = answers;
    }
}
