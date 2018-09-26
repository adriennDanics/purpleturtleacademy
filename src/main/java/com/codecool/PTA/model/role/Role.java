package com.codecool.PTA.model.role;

import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.model.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<Student> users = new HashSet<Student>();

    public Role() {}

    public Role(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Student> getUsers() {
        return users;
    }

    public void setUsers(Set<Student> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role: [id =" + id + "role = " + role + "]";
    }
}
