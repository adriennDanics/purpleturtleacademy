package com.codecool.PTA.user;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    protected User() {
    }

    public User(String username, String password) {
        LocalDate localDate = java.time.LocalDate.now();
        Date dateNow = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        this.username = username;
        this.password = password;
        this.registrationDate = dateNow;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

        public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ptaPU");
        EntityManager em = emf.createEntityManager();

        Student student = new Student("student", "pass");
        student.setEmail("email");
        student.setFirstName("first");
        student.setLastName("last");
        Mentor mentor = new Mentor("mentor", "pass");
        mentor.setEmail("email");
        mentor.setFirstName("first");
        mentor.setLastName("last");

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(student);
        em.persist(mentor);
        transaction.commit();

        em.close();
        emf.close();

    }

}
