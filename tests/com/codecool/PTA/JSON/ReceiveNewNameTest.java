package com.codecool.PTA.controller;

import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.user.GenderEnum;
import com.codecool.PTA.model.user.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ReceiveNewNameTest extends ControllerTest{

    @BeforeEach
    void setup() {
        createExamples();
        super.mockClasses();
    }

    private void createExamples() {
        Course course = new Course(CourseType.ORIENTATION, "orientation");
        GenderEnum gender = GenderEnum.OTHER;
        this.student = new Student("username", "password", "first_name", "last_name", "email", course, gender);
    }

    @Test
    void testGetNameForAppropriateStudentFromJSON() throws IOException, ServletException{
        String json = "Honeybunch";
        when(req.getParameter("password")).thenReturn(student.getPassword());
        when(req.getReader()).thenReturn(new BufferedReader(new StringReader(json)));
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("student")).thenReturn(student);

        new RegistrationController(pim, hash).doPost(req, resp);

        assertEquals("Honeybunch", student.getUsername());
    }
}
