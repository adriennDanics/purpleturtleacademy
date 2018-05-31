package com.codecool.PTA.quest;

import com.codecool.PTA.user.Level;

import javax.persistence.*;

@MappedSuperclass
public abstract class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    Level level;

    @Enumerated(EnumType.STRING)
    CourseType courseType;


    @Column(unique = true, nullable = false)
    private String assignmentTitle;
    @Column(nullable = false)
    private String question;

    public Assignment(Level level, CourseType courseType, String assignmentTitle, String question) {
        this.level = level;
        this.courseType = courseType;
        this.assignmentTitle = assignmentTitle;
        this.question = question;
    }

    public Assignment() {
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Level getLevel() {
        return level;
    }

    public CourseType getCourseType() {
        return courseType;
    }
}
