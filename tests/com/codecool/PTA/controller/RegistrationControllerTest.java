package com.codecool.PTA.controller;

import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.user.GenderEnum;
import com.codecool.PTA.model.user.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RegistrationControllerTest extends ControllerTest {

    @BeforeEach
    private void setup() {
        createExamples();
        super.mockClasses();
    }

    private void createExamples() {
        Course course = new Course(CourseType.ORIENTATION, "orientation");
        GenderEnum gender = GenderEnum.OTHER;
        this.student = new Student("username", "password", "first_name", "last_name", "email", course, gender);
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
        when(req.getParameter("gender")).thenReturn("other");
        when(req.getSession()).thenReturn(session);

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        doNothing().when(session).setAttribute(anyString(), captor.capture());

        when(passwordHashing.hashPassword(anyString())).thenReturn("password");

        when(pim.persist(any(Student.class))).thenReturn(true);
        when(pim.findCourseByName(any())).thenReturn(new Course(CourseType.ORIENTATION, "orientation"));

        new RegistrationController(pim, passwordHashing).doPost(req, resp);

        Student sessionStudent = captor.getValue();

        assertEquals(student, sessionStudent);
    }

}