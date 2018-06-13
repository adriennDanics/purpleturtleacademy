package com.codecool.PTA.controller;

import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.model.quest.Kata;
import com.codecool.PTA.model.quest.PA;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.persistence.PersistenceImplementation;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class KataController extends AbstractController {

    private PersistenceImplementation persistenceImplementation;

    public KataController(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkLogin(req)) {
            long id = Long.valueOf(req.getParameter("id"));

            WebContext context = new WebContext(req, resp, req.getServletContext());
            Kata kata = persistenceImplementation.findKataById(id);
            HttpSession session = req.getSession();
            Student student = (Student) session.getAttribute("student");
            context.setVariable("student", student);
            context.setVariable("kata", kata);

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("kata/katas.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));

        Kata kata = persistenceImplementation.findKataById(id);
        String submission = req.getParameter("submission");
        Student student = (Student) getLoggedInUser(req);
        kata.setSubmission(submission);
        kata.addStudent(student);
        persistenceImplementation.merge(kata);
        resp.sendRedirect("/assignments");
    }

}
