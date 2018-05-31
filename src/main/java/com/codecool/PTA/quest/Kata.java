package com.codecool.PTA.quest;

import com.codecool.PTA.user.Level;
import com.codecool.PTA.user.Student;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Kata extends Assignment {

    @Column(length = 1023)
    private String submission;

    @ManyToMany
    private Set<Student> student;

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
