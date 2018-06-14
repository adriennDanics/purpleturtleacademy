package com.codecool.PTA.controller;

import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.persistence.PersistenceImplementation;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class ListUsersController extends AbstractController {

    private PersistenceImplementation persistenceImplementation;

    public ListUsersController(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Student loggedInStudent = (Student) session.getAttribute("student");
        Set<Student> pendingFriends = loggedInStudent.getPendingFriends();
        Set<Student> friendList = loggedInStudent.getFriends();

        List<Student> studentList = persistenceImplementation.findAllStudents();
        studentList.remove(loggedInStudent);
        studentList.removeAll(pendingFriends);
        studentList.removeAll(friendList);

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("studentList", studentList);
        context.setVariable("student", loggedInStudent);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        engine.process("listStudents/listStudents.html", context, resp.getWriter());

    }
}
