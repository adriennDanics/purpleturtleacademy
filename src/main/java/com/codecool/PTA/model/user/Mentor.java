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

    public Mentor(String username, String password, String firstName, String lastName, String email, GenderEnum gender) {
        super(username, password, firstName, lastName, email, gender);
    }
}
