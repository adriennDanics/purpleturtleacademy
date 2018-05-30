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
    private EntityManagerFactory emf;

    private PersistenceImplementation() {
        emf = Persistence.createEntityManagerFactory("ptaPU");
    }

    public static PersistenceImplementation getInstance() {
        if (instance == null) {
            instance = new PersistenceImplementation();
        }
        return instance;
    }

    public EntityManager getEntityManagerInstance() {
        return emf.createEntityManager();
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(object);
        transaction.commit();

        em.close();
    }

    public void merge(Object object) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(object);
        transaction.commit();

        em.close();
    }

    public Student findStudentById(long id) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Student student = em.find(Student.class, id);
        transaction.commit();

        em.close();

        return student;
    }

    public Mentor findMentorById(long id) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Mentor mentor = em.find(Mentor.class, id);
        transaction.commit();

        em.close();

        return mentor;
    }

    public PA findPaById(long id) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        PA pa = em.find(PA.class, id);
        transaction.commit();

        em.close();

        return pa;
    }

    public QuizQuestion findQuizQuestionById(long id) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        QuizQuestion quizQuestion = em.find(QuizQuestion.class, id);
        transaction.commit();

        em.close();

        return quizQuestion;
    }

    public Kata findKataById(long id) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Kata kata = em.find(Kata.class, id);
        transaction.commit();

        em.close();

        return kata;
    }

    public Course findCourseById(long id) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Course course = em.find(Course.class, id);
        transaction.commit();

        em.close();

        return course;
    }

    public List findAllCourses() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("from Course c");
        List<Course> results = (List<Course>) query.getResultList();

        return results;
    }

}
