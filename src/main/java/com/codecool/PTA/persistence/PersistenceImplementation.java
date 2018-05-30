package com.codecool.PTA.persistence;

import com.codecool.PTA.user.Mentor;
import com.codecool.PTA.user.Student;

import javax.persistence.*;

public class PersistenceImplementation {

    private static PersistenceImplementation instance = null;

    private PersistenceImplementation() {
    }

    public static PersistenceImplementation getInstance() {
        if (instance == null) {
            instance = new PersistenceImplementation();
        }
        return instance;
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ptaPU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(object);
        transaction.commit();

        em.close();
        emf.close();
    }

//    public static void main(String[] args) {
//        Student student = new Student("student", "password");
//        Mentor mentor = new Mentor("mentor", "password");
//        PersistenceImplementation.getInstance().persist(student);
//        PersistenceImplementation.getInstance().persist(mentor);
//
//        Student random = new Student("random", "randomPass");
//        PersistenceImplementation.getInstance().persist(random);
//
//        Student some = new Student("some", "somePass");
//        PersistenceImplementation.getInstance().persist(some);
//
//        Student theNewOne = new Student("theNewOne", "theNewPass");
//        PersistenceImplementation.getInstance().persist(theNewOne);
//    }

}
