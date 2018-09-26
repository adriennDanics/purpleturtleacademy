package com.codecool.PTA.model.quest;

import com.codecool.PTA.model.user.Student;

import javax.persistence.*;

@Entity
public class PASolution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private PA pa;

    @ManyToOne
    private Student student;

    private String solution;

    public PASolution() {
    }

    public PASolution(PA pa, Student student) {
        this.pa = pa;
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PA getPA() {
        return pa;
    }

    public void setPA(PA pa) {
        this.pa = pa;
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
