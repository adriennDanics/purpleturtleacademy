package com.codecool.PTA.model.user;

import com.codecool.PTA.model.certificate.Certificate;
import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.quest.Kata;
import com.codecool.PTA.model.quest.PA;
import com.codecool.PTA.model.role.Role;
import com.codecool.PTA.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student extends User {

    private long xp;

    @Enumerated(EnumType.STRING)
    private Level level;

    @ManyToOne
    private Course course;

    @ManyToMany(mappedBy = "student")
    private Set<PA> completedPAs;

    @ManyToMany(mappedBy = "student")
    private Set<Kata> completedKatas;

    @OneToOne
    private Certificate certificate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="Friendship",
            joinColumns=@JoinColumn(name="Student"),
            inverseJoinColumns=@JoinColumn(name="Friend"))
    private Set<Student> friends = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="PendingFriendship",
            joinColumns=@JoinColumn(name="Student"),
            inverseJoinColumns=@JoinColumn(name="PendingFriend"))
    private Set<Student> pendingFriends = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="StudentTagedByOthers",
            joinColumns=@JoinColumn(name="Student"),
            inverseJoinColumns=@JoinColumn(name="TaggerFriend"))
    private Set<Student> taggedByOthers = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "roles",
//            joinColumns = {@JoinColumn(name = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id")}
//    )
    private Set<Role> roles = new HashSet<>();

    public Student() {
        super();
        LocalDate localDate = java.time.LocalDate.now();
        Date dateNow = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        this.setGender(GenderEnum.OTHER);
        this.setRegistrationDate(dateNow);
        this.xp = 0;
        this.level = Level.BEGINNER;
    }

    public Student(String username, String password, String firstName, String lastName, String email, Course course, GenderEnum gender) {
        super(username, password, firstName, lastName, email, gender);
        this.xp = 0;
        this.level = Level.BEGINNER;
        this.course = course;
    }

    public void addToFriends(Student student) {
        friends.add(student);
    }

    public void addToPendingFriends(Student student) {
        pendingFriends.add(student);
    }

    public void addToTaggedByOthers(Student student) {
        taggedByOthers.add(student);
    }

    public void removeFromFriends(Student student) {
        friends.remove(student);
    }

    public void removeFromPendingFriends(Student student) {
        pendingFriends.remove(student);
    }

    public void removeFromTaggedByOthers(Student student) {
        taggedByOthers.remove(student);
    }

    public Set<Student> getFriends() {
        return friends;
    }

    public Set<Student> getPendingFriends() {
        return pendingFriends;
    }

    public Set<Student> getTaggedByOthers() {
        return taggedByOthers;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public long getXp() {
        return xp;
    }

    public void setXp(long xp) {
        this.xp = xp;
        checkIfLevelJump();
    }

    private void checkIfLevelJump(){
        if(level.getNextLevel().getEntryRequirement() <= xp){
            this.level = level.getNextLevel();
        }
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Set<PA> getCompletedPAs() {
        return completedPAs;
    }

    public void setCompletedPAs(Set<PA> completedPAs) {
        this.completedPAs = completedPAs;
    }

    public Set<Kata> getCompletedKatas() {
        return completedKatas;
    }

    public void setCompletedKatas(Set<Kata> completedKatas) {
        this.completedKatas = completedKatas;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
