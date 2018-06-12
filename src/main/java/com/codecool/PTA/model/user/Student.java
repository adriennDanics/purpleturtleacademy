package com.codecool.PTA.model.user;

import com.codecool.PTA.model.certificate.Certificate;
import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.quest.Kata;
import com.codecool.PTA.model.quest.PA;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student extends User {

    private long xp;

    @Enumerated(EnumType.STRING)
    private Level level;

    @ManyToOne
    private Course course;

    @ManyToMany(mappedBy = "student")
    private Set<PA> completedPAs;

    @ManyToMany(mappedBy = "student")
    private Set<Kata> completedKatas;


    @OneToOne
    private Certificate certificate;

    protected Student() {
        super();
    }

    public Student(String username, String password, String firstName, String lastName, String email, Course course) {
        super(username, password, firstName, lastName, email);
        this.xp = 0;
        this.level = Level.BEGINNER;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public long getXp() {
        return xp;
    }

    public void setXp(long xp) {
        this.xp = xp;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Set<PA> getCompletedPAs() {
        return completedPAs;
    }

    public void setCompletedPAs(Set<PA> completedPAs) {
        this.completedPAs = completedPAs;
    }

    public Set<Kata> getCompletedKatas() {
        return completedKatas;
    }

    public void setCompletedKatas(Set<Kata> completedKatas) {
        this.completedKatas = completedKatas;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

}
