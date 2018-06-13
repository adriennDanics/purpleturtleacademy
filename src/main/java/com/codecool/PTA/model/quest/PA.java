package com.codecool.PTA.model.quest;

import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.model.user.Student;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class PA extends Assignment{

    private String submission;

    @ManyToMany()
    private Set<Student> student;

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

    public Set<Student> getStudent() {
        return student;
    }

    public void addStudent(Student student) {
        this.student.add(student);
    }
}
