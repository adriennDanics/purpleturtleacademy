package com.codecool.PTA.quest;

import com.codecool.PTA.user.Student;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Kata extends Assignment {

    @ManyToMany
    private Set<Student> student;

}
