package com.codecool.PTA.quest;

import com.codecool.PTA.user.Level;

import javax.persistence.*;

@Entity
public class PA extends Assignment{

    private String submission;

    protected PA() {
        super();
    }

    public PA(Level level, CourseType courseType, String assignmentTitle, String question) {
        super(level, courseType, assignmentTitle, question);
    }

    public String getSubmission() {
        return submission;
    }

    public void setSubmission(String submission) {
        this.submission = submission;
    }

}
