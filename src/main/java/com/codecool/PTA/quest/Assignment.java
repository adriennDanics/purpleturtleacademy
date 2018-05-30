package com.codecool.PTA.quest;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@MappedSuperclass
public abstract class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String assignmentTitle;
    private String decription;

    public Assignment(String assignmentTitle) {
            this.assignmentTitle = assignmentTitle;
            }

    public Assignment() {
    }

}
