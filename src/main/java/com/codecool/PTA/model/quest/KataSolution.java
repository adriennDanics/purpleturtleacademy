package com.codecool.PTA.model.quest;

import com.codecool.PTA.model.user.Student;

import javax.persistence.*;

@Entity
public class KataSolution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Kata kata;

    @ManyToOne
    private Student student;

    private String solution;

    public KataSolution() {
    }

    public KataSolution(Kata kata, Student student) {
        this.kata = kata;
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Kata getKata() {
        return kata;
    }

    public void setKata(Kata kata) {
        this.kata = kata;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
