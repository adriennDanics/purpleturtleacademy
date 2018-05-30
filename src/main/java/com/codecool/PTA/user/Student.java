package com.codecool.PTA.user;

import javax.persistence.*;

@Entity
public class Student extends User {

    private long xp;

    @Enumerated(EnumType.STRING)
    private Level level;

    protected Student() {
        super();
    }

    public Student(String username, String password) {
        super(username, password);
        this.xp = 0;
        this.level = Level.BEGINNER;
    }
}
