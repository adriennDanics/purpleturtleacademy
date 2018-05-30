package com.codecool.PTA.quest;

import javax.persistence.*;

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

    public long getId() {
        return id;
    }
}
