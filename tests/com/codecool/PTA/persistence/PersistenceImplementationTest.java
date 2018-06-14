package com.codecool.PTA.persistence;

import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.user.GenderEnum;
import com.codecool.PTA.model.user.Student;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersistenceImplementationTest {

    private PersistenceImplementation persistenceImplementation;

    private Student student;
    private Course course;
    private GenderEnum gender;

    private void createStudentExample() {
        this.student = new Student("username", "password", "first_name", "last_name", "email", course, gender);
    }

    private void createCourse() {
        this.course = new Course(CourseType.ORIENTATION, "orientation");
    }

    private void createGender() {
        this.gender = GenderEnum.OTHER;
    }

    @BeforeAll
    void setup() {
        createCourse();
        createGender();
        createStudentExample();
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
        persistenceImplementation.persist(course);
        persistenceImplementation.persist(student);

        assertThrows(IllegalArgumentException.class, () -> persistenceImplementation.findStudentById(666L));
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