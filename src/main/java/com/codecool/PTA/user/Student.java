package com.codecool.PTA.user;

import com.codecool.PTA.course.Course;

import javax.persistence.*;

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
}
