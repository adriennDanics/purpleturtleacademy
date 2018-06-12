package com.codecool.PTA.model.user;

import com.codecool.PTA.model.course.Course;

import javax.persistence.*;

@Entity
public class Mentor extends User {

    @ManyToOne
    private Course course;

    public Mentor() {
        super();
    }

    public Mentor(String username, String password) {
        super(username, password);
    }
}
