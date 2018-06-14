package com.codecool.PTA.persistence;

import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.*;
import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.model.user.Mentor;
import com.codecool.PTA.model.user.Student;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;

public class PersistenceImplementation {

    private final EntityManager em;

    public PersistenceImplementation(String persistenceUnit) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
        em = emf.createEntityManager();
    }

    public EntityManager getEm() {
        return em;
    }

    public boolean persist(Object object) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(object);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean merge(Object object) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.merge(object);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Student findStudentById(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Student student = em.find(Student.class, id);
        transaction.commit();
        if (student == null) throw new IllegalArgumentException("No student was found in DB with id " + id + ".");
        return student;
    }

    public Mentor findMentorById(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Mentor mentor = em.find(Mentor.class, id);
        transaction.commit();
        if (mentor == null) throw new IllegalArgumentException("No mentor was found in DB with id " + id + ".");
        return mentor;
    }

    public PA findPaById(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        PA pa = em.find(PA.class, id);
        transaction.commit();
        if (pa == null) throw new IllegalArgumentException("No PA was found in DB with id " + id + ".");
        return pa;
    }


    public QuizQuestion findQuizQuestionById(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        QuizQuestion quizQuestion = em.find(QuizQuestion.class, id);
        transaction.commit();
        if (quizQuestion == null)
            throw new IllegalArgumentException("No quiz question was found in DB with id " + id + ".");
        return quizQuestion;
    }

    public Kata findKataById(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Kata kata = em.find(Kata.class, id);
        transaction.commit();
        if (kata == null) throw new IllegalArgumentException("No kata was found in DB with id " + id + ".");
        return kata;
    }

    public Course findCourseById(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Course course = em.find(Course.class, id);
        transaction.commit();
        if (course == null) throw new IllegalArgumentException("No course was found in DB with id " + id + ".");
        return course;
    }

    public List<Student> findAllStudents() throws IOException {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Student> students = em.createQuery("FROM Student", Student.class).getResultList();
        transaction.commit();
        if (students.size() == 0) throw new IOException("No students were found in DB.");
        return students;
    }

    public List<Course> findAllCourses() throws IOException {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Course> courses = em.createQuery("FROM Course", Course.class).getResultList();
        transaction.commit();
        if (courses.size() == 0) throw new IOException("No courses were found in DB.");
        return courses;
    }

    public List<QuizQuestion> findAllQuizQuestions(CourseType type, Level level) throws IOException {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("FROM QuizQuestion WHERE courseType=:type AND level=:level", QuizQuestion.class);
        query.setParameter("type", type);
        query.setParameter("level", level);
        List<QuizQuestion> quizQuestions = query.getResultList();
        transaction.commit();
        if (quizQuestions.size() == 0) throw new IOException("No quiz questions were found in DB.");
        return quizQuestions;

    }

    public List<PA> findAllPaAssignments(CourseType type, Level level) throws IOException {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("FROM PA WHERE courseType=:type AND level=:level", PA.class);
        query.setParameter("type", type);
        query.setParameter("level", level);
        List<PA> PAs = query.getResultList();
        transaction.commit();
        if (PAs.size() == 0) throw new IOException("No PAs were found in DB.");
        return PAs;

    }

    public List<Kata> findAllKatas(CourseType type, Level level) throws IOException {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("FROM Kata WHERE courseType=:type AND level=:level", Kata.class);
        query.setParameter("type", type);
        query.setParameter("level", level);
        List<Kata> katas = query.getResultList();
        transaction.commit();
        if (katas.size() == 0) throw new IOException("No katas were found in DB.");
        return katas;
    }

    public List<FillInAnswer> findFillInAnswersForQuestion(long id) throws IOException {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<FillInAnswer> answers = em.createQuery(
                "SELECT answer FROM FillInAnswer AS answer " + "WHERE answer.question.id=:id",
                FillInAnswer.class
        ).setParameter("id", id).getResultList();
        transaction.commit();
        if (answers.size() == 0) throw new IOException("No answers were found in DB.");
        return answers;
    }

    public Course findCourseByName(CourseType type) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("from Course where name=:name", Course.class);
        query.setParameter("name", type);
        Course course = (Course) query.getSingleResult();
        transaction.commit();
        if (course == null) throw new IllegalArgumentException("No course was found in DB with the specified name.");
        return course;
    }

    public List<FillInTheBlank> findAllFillInTheBlank(CourseType type, Level level){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("FROM FillInTheBlank WHERE courseType=:type AND level=:level", FillInTheBlank.class);
        query.setParameter("type", type);
        query.setParameter("level", level);
        List<FillInTheBlank> fillInTheBlankList = query.getResultList();
        transaction.commit();
        return fillInTheBlankList;
    }
}
