package com.codecool.PTA.user;

import javax.persistence.*;

@Entity
public class Mentor extends User {

    protected Mentor() {
        super();
    }

    public Mentor(String username, String password) {
        super(username, password);
    }
}
