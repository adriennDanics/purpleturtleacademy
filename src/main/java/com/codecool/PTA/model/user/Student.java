package com.codecool.PTA.model.user;

import com.codecool.PTA.model.certificate.Certificate;
import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.quest.Kata;
import com.codecool.PTA.model.quest.KataSolution;
import com.codecool.PTA.model.quest.PA;
import com.codecool.PTA.model.role.Role;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Student")
@DynamicUpdate
public class Student extends User {

    private long id;
    private long xp;
    private Level level;
    private Course course;
    private Set<PA> completedPAs = new HashSet<>();
//    private Set<KataSolution> completedKatas = new HashSet<>();
    private Certificate certificate;
    private Set<Student> friends = new HashSet<>();
    private Set<Student> pendingFriends = new HashSet<>();
    private Set<Student> taggedByOthers = new HashSet<>();
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "Friendship",
            joinColumns = @JoinColumn(name = "Student"),
            inverseJoinColumns = @JoinColumn(name = "Friend"))
    public Set<Student> getFriends() {
        return friends;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "Pending_Friendship",
            joinColumns = @JoinColumn(name = "Student"),
            inverseJoinColumns = @JoinColumn(name = "Pending_Friend"))
    public Set<Student> getPendingFriends() {
        return pendingFriends;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "Student_Tagged_By_Others",
            joinColumns = @JoinColumn(name = "Student"),
            inverseJoinColumns = @JoinColumn(name = "Tagger_Friend"))
    public Set<Student> getTaggedByOthers() {
        return taggedByOthers;
    }

    @ManyToOne
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

    private void checkIfLevelJump() {
        if (level.getNextLevel().getEntryRequirement() <= xp) {
            this.level = level.getNextLevel();
        }
    }

    @Enumerated(EnumType.STRING)
    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "pa_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "completedpas_id"))
    public Set<PA> getCompletedPAs() {
        return this.completedPAs;
    }

    public void setCompletedPAs(Set<PA> completedPAs) {
        this.completedPAs = completedPAs;
    }

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "student")
//    public Set<KataSolution> getCompletedKatas() {
//        return this.completedKatas;
//    }
//
//    public void setCompletedKatas(Set<KataSolution> completedKatas) {
//        this.completedKatas = completedKatas;
//    }

    @OneToOne
    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFriends(Set<Student> friends) {
        this.friends = friends;
    }

    public void setPendingFriends(Set<Student> pendingFriends) {
        this.pendingFriends = pendingFriends;
    }

    public void setTaggedByOthers(Set<Student> taggedByOthers) {
        this.taggedByOthers = taggedByOthers;
    }

}
