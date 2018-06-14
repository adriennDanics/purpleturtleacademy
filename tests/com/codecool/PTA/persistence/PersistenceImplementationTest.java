package com.codecool.PTA.persistence;

import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.Kata;
import com.codecool.PTA.model.quest.PA;
import com.codecool.PTA.model.quest.QuizQuestion;
import com.codecool.PTA.model.user.GenderEnum;
import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.model.user.Mentor;
import com.codecool.PTA.model.user.Student;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersistenceImplementationTest {

    private PersistenceImplementation persistenceImplementation;

    private Student student;
    private Mentor mentor;
    private Course course;
    private PA pa;
    private QuizQuestion quizQuestion;
    private Kata kata;

    private void createExamples() {
        GenderEnum gender = GenderEnum.OTHER;
        this.course = new Course(CourseType.PYTHON, "Python");
        this.student = new Student("username", "password", "first_name", "last_name", "email", course, gender);
        this.mentor = new Mentor("username", "password", "mentorFirstName", "mentorLastName", "mentorEmail", gender);
        this.pa = new PA(Level.BEGINNER, CourseType.PYTHON, "PA title", "PA question");
        createExampleQuizQuestion();
        this.kata = new Kata(Level.BEGINNER, CourseType.PYTHON, "kata title", "kata question");
    }

    private void createExampleQuizQuestion() {
        Map<String, Boolean> quizQuestionAnswers = new HashMap<>();
        quizQuestionAnswers.put("str.replace", true);
        quizQuestionAnswers.put("str.replace()", false);
        quizQuestionAnswers.put("str.replace(old, new[, count])", false);
        quizQuestionAnswers.put("str.replace(old, new)", false);
        this.quizQuestion = new QuizQuestion("What is the built-in function for replacing a substring in a string?", "Replacing substrings", Level.BEGINNER, CourseType.PYTHON, quizQuestionAnswers);
    }

    @BeforeAll
    void setup() {
        this.persistenceImplementation = new PersistenceImplementation("pta-testPU");
        createExamples();
    }

    @Test
    void testPersist() {
        assertTrue(persistenceImplementation.persist(course));
        assertTrue(persistenceImplementation.persist(student));
    }

    @Test
    void testMerge() {
        persistenceImplementation.persist(course);
        persistenceImplementation.persist(student);
        student.setEmail("new@email.set");

        assertTrue(persistenceImplementation.merge(student));
    }

    @Test
    void testFindExistingStudentById() {
        persistenceImplementation.persist(course);
        persistenceImplementation.persist(student);

        assertEquals(student, persistenceImplementation.findStudentById(1L));
    }

    @Test
    void testFindNonExistentStudentById() {
        assertThrows(IllegalArgumentException.class, () -> persistenceImplementation.findStudentById(666L));
    }

    @Test
    void findExistingMentorById() {
        persistenceImplementation.persist(mentor);

        assertEquals(mentor, persistenceImplementation.findMentorById(1L));
    }

    @Test
    void findNonExistentMentorById() {
        assertThrows(IllegalArgumentException.class, () -> persistenceImplementation.findMentorById(666L));
    }

    @Test
    void findExistingPaById() {
        persistenceImplementation.persist(pa);

        assertEquals(pa, persistenceImplementation.findPaById(1L));
    }

    @Test
    void findNonExistentPaById() {
        assertThrows(IllegalArgumentException.class, () -> persistenceImplementation.findPaById(666L));
    }

    @Test
    void findExistingQuizQuestionById() {
        persistenceImplementation.persist(quizQuestion);

        assertEquals(quizQuestion, persistenceImplementation.findQuizQuestionById(1L));
    }

    @Test
    void findNonExistentQuizQuestionById() {
        assertThrows(IllegalArgumentException.class, () -> persistenceImplementation.findQuizQuestionById(666L));
    }

    @Test
    void findExistingKataById() {
        persistenceImplementation.persist(kata);

        assertEquals(kata, persistenceImplementation.findKataById(1L));
    }

    @Test
    void findNonExistentKataById() {
        assertThrows(IllegalArgumentException.class, () -> persistenceImplementation.findKataById(666L));
    }

    @Test
    void findExistingCourseById() {
        persistenceImplementation.persist(course);

        assertEquals(course, persistenceImplementation.findCourseById(1L));
    }

    @Test
    void findNonExistentCourseById() {
        assertThrows(IllegalArgumentException.class, () -> persistenceImplementation.findCourseById(666L));
    }

    @Test
    void findAllStudentsIfExist() throws IOException {
        persistenceImplementation.persist(course);
        persistenceImplementation.persist(student);
        List<Student> students = new ArrayList<>();
        students.add(student);

        List<Student> studentsInDB = persistenceImplementation.findAllStudents();
        assertEquals(students, studentsInDB);
    }

    @Test
    void findAllCourses() {
        assertEquals(1, 2);
    }

    @Test
    void findAllQuizQuestion() {
        assertEquals(1, 2);
    }

    @Test
    void findAllPaAssignments() {
        assertEquals(1, 2);
    }

    @Test
    void findFillInAnswersForQuestion() {
        assertEquals(1, 2);
    }

    @Test
    void findCourseByName() {
        assertEquals(1, 2);
    }
}