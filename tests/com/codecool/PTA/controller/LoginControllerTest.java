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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class LoginControllerTest extends ControllerTest {

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
    void testFindAppropriateStudent() throws ServletException, IOException {
        when(req.getSession()).thenReturn(session);
        when(req.getParameter("username")).thenReturn("username");
        when(req.getParameter("password")).thenReturn("password");

        List<Student> students = new ArrayList<>();
        students.add(student);
        when(pim.findAllStudents()).thenReturn(students);

        when(hash.isPasswordCorrect(anyString(), anyString())).thenReturn(true);

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        doNothing().when(session).setAttribute(anyString(), captor.capture());

        new LoginController(pim, hash).doPost(req, resp);

        Student sessionStudent = captor.getValue();

        assertEquals(student, sessionStudent);
    }

}