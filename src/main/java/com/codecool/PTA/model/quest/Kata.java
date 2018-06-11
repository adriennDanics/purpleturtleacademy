package com.codecool.PTA.model.quest;


import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.model.user.Student;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Kata extends Assignment {


    @ManyToMany
    private Set<Student> student;

    @Column(length = 1023)
    private String submission;

    protected Kata() {
        super();
    }

    public Kata(Level level, CourseType courseType, String assignmentTitle, String question) {
        super(level, courseType, assignmentTitle, question);
    }

    public String getSubmission() {
        return submission;
    }

    public void setSubmission(String submission) {
        this.submission = submission;
    }

}
