package com.codecool.PTA.model.quest;

import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.model.user.Student;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Kata extends Assignment {


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "kata_student",
            joinColumns = @JoinColumn(name = "kata_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> studentKataSubmissions = new HashSet<>();

    @Column(length = 1023)
    private String submission;

    @Column
    public boolean isTemplate;

    protected Kata() {
        super();
    }

    public Kata(Level level, CourseType courseType, String assignmentTitle, String question, boolean isTemplate) {
        super(level, courseType, assignmentTitle, question);
        this.isTemplate = isTemplate;
    }

    public String getSubmission() {
        return submission;
    }

    public void setSubmission(String submission) {
        this.submission = submission;
    }

    public Set<Student> getStudentKataSubmissions() {
        return studentKataSubmissions;
    }

    public void addStudent(Student student) {
        this.studentKataSubmissions.add(student);
    }

}
