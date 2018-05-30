package com.codecool.PTA.user;

import com.codecool.PTA.course.Course;
import com.codecool.PTA.persistence.PersistenceImplementation;

import javax.persistence.*;
import java.util.List;

@NamedQuery(name="Student.findAllStudents",
        query="SELECT s FROM Student s")
@Entity
public class Student extends User {

    @Transient
    static public List<Student> studentList = PersistenceImplementation.getInstance().getEntityManagerInstance().createNamedQuery("Student.findAllStudents", Student.class).getResultList();

    private long xp;

    @Enumerated(EnumType.STRING)
    private Level level;

    @ManyToOne
    private Course course;


    protected Student() {
        super();
    }

    public Student(String username, String password) {
        super(username, password);
        this.xp = 0;
        this.level = Level.BEGINNER;
    }
}
