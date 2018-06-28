package com.codecool.PTA.model.course;

//import com.codecool.PTA.model.user.Mentor;
import com.codecool.PTA.model.user.Student;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(value = EnumType.STRING)
    private CourseType name;

    private String description;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();

//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
//    private Set<Mentor> mentors = new HashSet<>();

    protected Course(){}

    public Course(CourseType name, String description){
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CourseType getName() {
        return name;
    }

    public void setName(CourseType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Student student) {
        this.students.add(student);
    }

//    public Set<Mentor> getMentors() {
//        return mentors;
//    }
//
//    public void setMentors(Mentor mentor) {
//        this.mentors.add(mentor);
//    }
}
