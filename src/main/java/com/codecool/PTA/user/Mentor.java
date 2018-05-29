package com.codecool.PTA.user;

import javax.persistence.*;

@Entity
public class Mentor extends User {

    public Mentor() {
        super();
    }

    public Mentor(String username, String password) {
        super(username, password);
    }
}
