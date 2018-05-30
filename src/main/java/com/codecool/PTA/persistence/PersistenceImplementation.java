package com.codecool.PTA.persistence;

import com.codecool.PTA.course.Course;
import com.codecool.PTA.quest.Kata;
import com.codecool.PTA.quest.PA;
import com.codecool.PTA.quest.QuizQuestion;
import com.codecool.PTA.user.Mentor;
import com.codecool.PTA.user.Student;

import javax.persistence.*;
import java.util.List;

public class PersistenceImplementation {

    private static PersistenceImplementation instance = null;
    private final EntityManager em;

    private PersistenceImplementation() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ptaPU");
        em = emf.createEntityManager();
    }

    public static PersistenceImplementation getInstance() {
        if (instance == null) {
            instance = new PersistenceImplementation();
        }
        return instance;
    }

    public EntityManager getEm() {
        return em;
    }

    public void persist(Object object) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(object);
        transaction.commit();
        }

    public void merge(Object object) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(object);
        transaction.commit();
    }

    public Student findStudentById(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Student student = em.find(Student.class, id);
        transaction.commit();
        return student;
    }

    public Mentor findMentorById(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Mentor mentor = em.find(Mentor.class, id);
        transaction.commit();
        return mentor;
    }

    public PA findPaById(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        PA pa = em.find(PA.class, id);
        transaction.commit();
        return pa;
    }

    public QuizQuestion findQuizQuestionById(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        QuizQuestion quizQuestion = em.find(QuizQuestion.class, id);
        transaction.commit();
        return quizQuestion;
    }

    public Kata findKataById(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Kata kata = em.find(Kata.class, id);
        transaction.commit();
        return kata;
    }

    public Course findCourseById(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Course course = em.find(Course.class, id);
        transaction.commit();
        return course;
    }

//    public List<Student> findAllStudent() {
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//        List<Student> studentList = em.createQuery("FROM Student", Student.class).getResultList();
//        transaction.commit();
//        return studentList;
//    }

}
