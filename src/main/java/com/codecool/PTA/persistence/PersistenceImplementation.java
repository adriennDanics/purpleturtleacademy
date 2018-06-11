package com.codecool.PTA.persistence;

import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.quest.*;
import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.Kata;
import com.codecool.PTA.model.quest.PA;
import com.codecool.PTA.model.quest.QuizQuestion;
import com.codecool.PTA.model.user.Mentor;
import com.codecool.PTA.model.user.Student;

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

    public List<Student> findAllStudents() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Student> studentList = em.createQuery("FROM Student", Student.class).getResultList();
        transaction.commit();
        return studentList;
    }


    public List<Course> findAllCourses() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Course> results = em.createQuery("FROM Course", Course.class).getResultList();
        transaction.commit();
        return results;
    }

    public List<QuizQuestion> findAllQuizQuestion() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<QuizQuestion> quizQuestionList = em.createQuery("FROM QuizQuestion", QuizQuestion.class).getResultList();
        transaction.commit();
        return quizQuestionList;

    }

    public List<PA> findAllPaAssignments() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<PA> paList = em.createQuery("FROM PA", PA.class).getResultList();
        transaction.commit();
        return paList;

    }

    public List<FillInAnswer> findFillInAnswersForQuestion(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<FillInAnswer> answerList = em.createQuery("SELECT answer FROM FillInAnswer AS answer " +
                        "WHERE answer.question.id=:id",
                FillInAnswer.class).setParameter("id", id).getResultList();
        transaction.commit();
        return answerList;
    }
  
  
    public Course findCourseByName(CourseType type){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query= em.createQuery("from Course where name=:name", Course.class);
        query.setParameter("name", type);
        Course course = (Course) query.getSingleResult();
        transaction.commit();
        return course;
    }

}
