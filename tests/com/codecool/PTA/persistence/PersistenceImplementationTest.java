package com.codecool.PTA.persistence;

import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.*;
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
    private FillInTheBlank fillInTheBlank;
    private FillInAnswer fillInAnswer;


    private void createExamples() {
        GenderEnum gender = GenderEnum.OTHER;
        this.course = new Course(CourseType.PYTHON, "Python");
        this.student = new Student("username", "password", "first_name", "last_name", "email", course, gender);
        this.mentor = new Mentor("username", "password", "mentorFirstName", "mentorLastName", "mentorEmail", gender);
        this.pa = new PA(Level.BEGINNER, CourseType.PYTHON, "PA title", "PA question", false);
        createExampleQuizQuestion();
        this.kata = new Kata(Level.BEGINNER, CourseType.PYTHON, "kata title", "kata question", false);
        createFillIn();
    }

    private void createExampleQuizQuestion() {
        Map<String, Boolean> quizQuestionAnswers = new HashMap<>();
        quizQuestionAnswers.put("str.replace", true);
        quizQuestionAnswers.put("str.replace()", false);
        quizQuestionAnswers.put("str.replace(old, new[, count])", false);
        quizQuestionAnswers.put("str.replace(old, new)", false);
        this.quizQuestion = new QuizQuestion("What is the built-in function for replacing a substring in a string?", "Replacing substrings", Level.BEGINNER, CourseType.PYTHON, quizQuestionAnswers);
    }

    private void createFillIn() {
        this.fillInTheBlank = new FillInTheBlank(Level.BEGINNER,
                CourseType.PYTHON,
                "Please fill in the blank to print!",
                "<input type=\"text\" class=\"answer\" size=\"5\" >(\"Hello World!\")");
        this.fillInAnswer = new FillInAnswer("print", fillInTheBlank);
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
    void testFindExistingMentorById() {
        persistenceImplementation.persist(mentor);

        assertEquals(mentor, persistenceImplementation.findMentorById(1L));
    }

    @Test
    void testFindNonExistentMentorById() {
        assertThrows(IllegalArgumentException.class, () -> persistenceImplementation.findMentorById(666L));
    }

    @Test
    void testFindExistingPaById() {
        persistenceImplementation.persist(pa);

        assertEquals(pa, persistenceImplementation.findPaById(1L));
    }

    @Test
    void testFindNonExistentPaById() {
        assertThrows(IllegalArgumentException.class, () -> persistenceImplementation.findPaById(666L));
    }

    @Test
    void testFindExistingQuizQuestionById() {
        persistenceImplementation.persist(quizQuestion);

        assertEquals(quizQuestion, persistenceImplementation.findQuizQuestionById(1L));
    }

    @Test
    void testFindNonExistentQuizQuestionById() {
        assertThrows(IllegalArgumentException.class, () -> persistenceImplementation.findQuizQuestionById(666L));
    }

    @Test
    void testFindExistingKataById() {
        persistenceImplementation.persist(kata);

        assertEquals(kata, persistenceImplementation.findKataById(1L));
    }

    @Test
    void testFindNonExistentKataById() {
        assertThrows(IllegalArgumentException.class, () -> persistenceImplementation.findKataById(666L));
    }

    @Test
    void testFindExistingCourseById() {
        persistenceImplementation.persist(course);

        assertEquals(course, persistenceImplementation.findCourseById(1L));
    }

    @Test
    void testFindNonExistentCourseById() {
        assertThrows(IllegalArgumentException.class, () -> persistenceImplementation.findCourseById(666L));
    }

    @Test
    void testFindAllStudents() throws IOException {
        persistenceImplementation.persist(course);
        persistenceImplementation.persist(student);
        List<Student> students = new ArrayList<>();
        students.add(student);

        List<Student> studentsInDB = persistenceImplementation.findAllStudents();
        assertEquals(students, studentsInDB);
    }

    @Test
    void testFindAllCourses() throws IOException {
        persistenceImplementation.persist(course);
        List<Course> courses = new ArrayList<>();
        courses.add(course);

        List<Course> coursesInDB = persistenceImplementation.findAllCourses();
        assertEquals(courses, coursesInDB);
    }

    @Test
    void testFindAllQuizQuestion() throws IOException {
        persistenceImplementation.persist(quizQuestion);
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        quizQuestions.add(quizQuestion);

        List<QuizQuestion> quizQuestionsInDB = persistenceImplementation.findAllQuizQuestions(CourseType.PYTHON, Level.BEGINNER);
        assertEquals(quizQuestions, quizQuestionsInDB);
    }

    @Test
    void testFindAllPaAssignments() throws IOException {
        persistenceImplementation.persist(pa);
        List<PA> PAs = new ArrayList<>();
        PAs.add(pa);

        List<PA> PAsInDB = persistenceImplementation.findAllPaAssignments(CourseType.PYTHON, Level.BEGINNER);
        assertEquals(PAs, PAsInDB);
    }

    @Test
    void testFindAllKatas() throws IOException {
        persistenceImplementation.persist(kata);
        List<Kata> katas = new ArrayList<>();
        katas.add(kata);

        List<Kata> katasInDB = persistenceImplementation.findAllKatas(CourseType.PYTHON, Level.BEGINNER);
        assertEquals(katas, katasInDB);
    }

    @Test
    void testFindFillInAnswersForQuestion() throws IOException {
        persistenceImplementation.persist(fillInTheBlank);
        persistenceImplementation.persist(fillInAnswer);
        List<FillInAnswer> fillInAnswers = new ArrayList<>();
        fillInAnswers.add(fillInAnswer);

        List<FillInAnswer> fillInAnswersInDB = persistenceImplementation.findFillInAnswersForQuestion(1L);
        assertEquals(fillInAnswers, fillInAnswersInDB);
    }

    @Test
    void testFindCourseByName() {
        persistenceImplementation.persist(course);

        assertEquals(course, persistenceImplementation.findCourseByName(CourseType.PYTHON));
    }
}