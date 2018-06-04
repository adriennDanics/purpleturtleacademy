package com.codecool.PTA.user;

import com.codecool.PTA.course.Course;
import com.codecool.PTA.quest.Kata;
import com.codecool.PTA.quest.PA;

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

    protected Student() {
        super();
    }

    public Student(String username, String password) {
        super(username, password);
        this.xp = 0;
        this.level = Level.BEGINNER;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
