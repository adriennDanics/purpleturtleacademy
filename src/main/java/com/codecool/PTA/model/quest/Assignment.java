package com.codecool.PTA.model.quest;

import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.user.Level;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class Assignment implements Comparable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    Level level;

    @Enumerated(EnumType.STRING)
    CourseType courseType;

    @Column(unique = true, nullable = false)
    private String assignmentTitle;

    @Column(nullable = false, length = 1023)
    private String question;

    protected Assignment() {
    }

    public Assignment(Level level, CourseType courseType, String assignmentTitle, String question) {
        this.level = level;
        this.courseType = courseType;
        this.assignmentTitle = assignmentTitle;
        this.question = question;
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

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    @Override
    public int compareTo(Object o) {
        if(this.equals(o)){
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assignment)) return false;
        Assignment that = (Assignment) o;
        return getLevel() == that.getLevel() &&
                getCourseType() == that.getCourseType() &&
                Objects.equals(getAssignmentTitle(), that.getAssignmentTitle()) &&
                Objects.equals(getQuestion(), that.getQuestion());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getLevel(), getCourseType(), getAssignmentTitle(), getQuestion());
    }
}
