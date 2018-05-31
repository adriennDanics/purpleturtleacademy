package com.codecool.PTA.certificate;

import com.codecool.PTA.user.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Student student;

    @Temporal(TemporalType.DATE)
    private Date issueDate;

    protected Certificate() {}

    public Certificate(Student student) {
        LocalDate localDate = java.time.LocalDate.now();
        Date dateNow = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        this.student = student;
        this.issueDate = dateNow;
    }
}
