package com.codecool.PTA.persistence;

import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.PA;
import com.codecool.PTA.model.user.GenderEnum;
import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.model.user.Mentor;
import com.codecool.PTA.model.user.Student;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersistenceImplementationTest {

    private PersistenceImplementation persistenceImplementation;

    private Student student;
    private Mentor mentor;
    private Course course;
    private GenderEnum gender;
    private PA pa;

    private void createExamples() {
        this.course = new Course(CourseType.PYTHON, "Python");
        this.gender = GenderEnum.OTHER;
        this.student = new Student("username", "password", "first_name", "last_name", "email", course, gender);
        this.mentor = new Mentor("username", "password", "mentorFirstName", "mentorLastName", "mentorEmail", gender);
        this.pa = new PA(Level.BEGINNER, CourseType.PYTHON, "PA title", "PA question");
    }

    @BeforeAll
    void setup() {
        createExamples();
        this.persistenceImplementation = new PersistenceImplementation("pta-testPU");
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

        Student studentInDB = persistenceImplementation.findStudentById(1L);
        assertEquals(student, studentInDB);
    }

    @Test
    void testFindNonExistentStudentById() {
        assertThrows(IllegalArgumentException.class, () -> persistenceImplementation.findStudentById(666L));
    }

    @Test
    void findExistingMentorById() {
        persistenceImplementation.persist(mentor);

        Mentor mentorInDB = persistenceImplementation.findMentorById(1L);
        assertEquals(mentor, mentorInDB);
    }

    @Test
    void findNonExistentMentorById() {
        assertThrows(IllegalArgumentException.class, () -> persistenceImplementation.findMentorById(666L));
    }

    @Test
    void findExistingPaById() {
        persistenceImplementation.persist(pa);

        PA paInDB = persistenceImplementation.findPaById(1);
        assertEquals(pa, paInDB);
    }

    @Test
    void findNonExistentPaById() {
        assertThrows(IllegalArgumentException.class, () -> persistenceImplementation.findPaById(666L));
    }

    @Test
    void findQuizQuestionById() {
        assertEquals(1, 2);
    }

    @Test
    void findKataById() {
        assertEquals(1, 2);
    }

    @Test
    void findCourseById() {
        assertEquals(1, 2);
    }

    @Test
    void findAllStudents() {
        assertEquals(1, 2);
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