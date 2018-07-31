package com.codecool.PTA.model.quest;

import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.model.user.Student;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PA extends Assignment{

    private String submission;

    @ManyToMany
    private Set<Student> student = new HashSet<>();

    @Column
    public boolean isTemplate;

    protected PA() {
        super();
    }

    public PA(Level level, CourseType courseType, String assignmentTitle, String question, boolean isTemplate) {
        super(level, courseType, assignmentTitle, question);
        this.isTemplate = isTemplate;
    }

    public String getSubmission() {
        return submission;
    }

    public void setSubmission(String submission) {
        this.submission = submission;
    }

    public Set<Student> getStudent() {
        return student;
    }

    public void addStudent(Student student) {
        this.student.add(student);
    }
}
