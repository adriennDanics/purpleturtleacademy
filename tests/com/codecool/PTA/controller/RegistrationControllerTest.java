package com.codecool.PTA.controller;

import com.codecool.PTA.helper.Hash;
import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.persistence.PersistenceImplementation;
import org.junit.jupiter.api.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//TODO
class RegistrationControllerTest {

    private Student student;
    private Course course;

    @BeforeEach
    private void init() {
        createStudentExample();
        createCourse();
    }

    private void createStudentExample() {
        Student student = new Student("username", "password", "first_name", "last_name", "email", course);
        this.student = student;
    }

    private void createCourse() {
        this.course = new Course(CourseType.ORIENTATION, "orientation");
    }

    @Test
    void testEverythingOK() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("passwordNotMatch")).thenReturn(null);

        when(req.getParameter("username")).thenReturn("username");
        when(req.getParameter("password")).thenReturn("password");
        when(req.getParameter("password_confirm")).thenReturn("password");
        when(req.getParameter("first_name")).thenReturn("first_name");
        when(req.getParameter("last_name")).thenReturn("last_name");
        when(req.getParameter("email")).thenReturn("email");
        when(req.getSession()).thenReturn(session);
        doNothing().when(session).setAttribute("student", any());

        Hash hash = mock(Hash.class);
        when(hash.hashPassword(anyString())).thenReturn("password");

        PersistenceImplementation pim = mock(PersistenceImplementation.class);
        when(pim.persist(any(Student.class))).thenReturn(true);
        when(pim.findCourseByName(any())).thenReturn(new Course(CourseType.ORIENTATION, "orientation"));

        new RegistrationController(pim, hash).doPost(req, resp);

        assertEquals(student, req.getSession().getAttribute("student"));
    }
}