package com.codecool.PTA.controller;

import com.codecool.PTA.helper.Hash;
import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.persistence.PersistenceImplementation;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrationControllerTest {

    private Student student;
    private Course course;
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private HttpSession session;
    private Hash hash;
    private PersistenceImplementation pim;

    @BeforeEach
    private void init() {
        createStudentExample();
        createCourse();
        mockClasses();
    }

    private void mockClasses() {
        this.req = mock(HttpServletRequest.class);
        this.resp = mock(HttpServletResponse.class);
        this.session = mock(HttpSession.class);
        this.hash = mock(Hash.class);
        this.pim = mock(PersistenceImplementation.class);
    }

    private void createStudentExample() {
        this.student = new Student("username", "password", "first_name", "last_name", "email", course);
    }

    private void createCourse() {
        this.course = new Course(CourseType.ORIENTATION, "orientation");
    }

    @Test
    void testEverythingOK() throws ServletException, IOException {
        when(session.getAttribute("passwordNotMatch")).thenReturn(null);

        when(req.getParameter("username")).thenReturn("username");
        when(req.getParameter("password")).thenReturn("password");
        when(req.getParameter("password_confirm")).thenReturn("password");
        when(req.getParameter("first_name")).thenReturn("first_name");
        when(req.getParameter("last_name")).thenReturn("last_name");
        when(req.getParameter("email")).thenReturn("email");
        when(req.getSession()).thenReturn(session);

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        doNothing().when(session).setAttribute(anyString(), captor.capture());

        when(hash.hashPassword(anyString())).thenReturn("password");

        when(pim.persist(any(Student.class))).thenReturn(true);
        when(pim.findCourseByName(any())).thenReturn(new Course(CourseType.ORIENTATION, "orientation"));

        new RegistrationController(pim, hash).doPost(req, resp);
        Student sessionStudent = captor.getValue();

        assertEquals(student, sessionStudent);
    }

}