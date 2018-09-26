package com.codecool.PTA.model.user;

import com.codecool.PTA.model.role.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@MappedSuperclass
public abstract class User {



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

    @Enumerated(value = EnumType.STRING)
    private GenderEnum gender;

    private String image;

    protected User() {
    }

    public User(String username, String password, String firstName, String lastName, String email, GenderEnum gender) {
        LocalDate localDate = java.time.LocalDate.now();
        Date dateNow = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registrationDate = dateNow;
        this.gender = gender;
        this.image = gender.getImage();
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

    public void setGender(GenderEnum gender) {
        this.gender = gender;
        this.image = gender.getImage();
    }

    public void reSetDefaultImage() {
        this.image = gender.getImage();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(registrationDate, user.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getFirstName(), getLastName(), getEmail(), registrationDate);
    }
}
