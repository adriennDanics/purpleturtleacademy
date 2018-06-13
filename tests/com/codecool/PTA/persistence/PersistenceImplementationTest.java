package com.codecool.PTA.persistence;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersistenceImplementationTest {

    private EntityManager em;

    @BeforeAll
    void setup() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pta-testPU");
        this.em = emf.createEntityManager();
    }

    @Test
    void persist() {
    }

    @Test
    void merge() {
    }

    @Test
    void findStudentById() {
    }

    @Test
    void findMentorById() {
    }

    @Test
    void findPaById() {
    }

    @Test
    void findQuizQuestionById() {
    }

    @Test
    void findKataById() {
    }

    @Test
    void findCourseById() {
    }

    @Test
    void findAllStudents() {
    }

    @Test
    void findAllCourses() {
    }

    @Test
    void findAllQuizQuestion() {
    }

    @Test
    void findAllPaAssignments() {
    }

    @Test
    void findFillInAnswersForQuestion() {
    }

    @Test
    void findCourseByName() {
    }
}