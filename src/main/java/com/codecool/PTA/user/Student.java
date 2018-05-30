package com.codecool.PTA.user;

import com.codecool.PTA.course.Course;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class Student extends User {

    private long xp;

    @Enumerated(EnumType.STRING)
    private Level level;

    @ManyToOne
    private Course course;

    protected Student() {
        super();
    }

    public Student(String username, String password) {
        super(username, password);
        this.xp = 0;
        this.level = Level.BEGINNER;
    }
}
