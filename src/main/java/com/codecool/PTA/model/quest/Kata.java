package com.codecool.PTA.model.quest;

import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.user.Level;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Kata extends Assignment {

//    @Column(length = 1023)
//    private String submission;

    @Column
    public boolean isTemplate;

//    @OneToMany(mappedBy = "kata")
//    private List<KataSolution> kataSolution;

    protected Kata() {
        super();
    }

    public Kata(Level level, CourseType courseType, String assignmentTitle, String question, boolean isTemplate) {
        super(level, courseType, assignmentTitle, question);
        this.isTemplate = isTemplate;
    }

//    public String getSubmission() {
//        return submission;
//    }
//
//    public void setSubmission(String submission) {
//        this.submission = submission;
//    }

}
