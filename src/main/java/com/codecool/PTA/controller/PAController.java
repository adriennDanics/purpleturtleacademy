package com.codecool.PTA.controller;

import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.persistence.PersistenceImplementation;
import com.codecool.PTA.model.quest.PA;
import com.codecool.PTA.model.user.Student;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PAController extends AbstractController {
    
    private PersistenceImplementation persistenceImplementation;

    public PAController(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkLogin(req)) {
            long id = Long.valueOf(req.getParameter("id"));

            WebContext context = new WebContext(req, resp, req.getServletContext());
            PA question = persistenceImplementation.findPaById(id);
            HttpSession session = req.getSession();
            Student student = (Student) session.getAttribute("student");
            context.setVariable("student", student);
            context.setVariable("question", question);

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("pa/pas.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));

        PA pa = persistenceImplementation.findPaById(id);
        String submission = req.getParameter("submission");
        Student student = (Student) getLoggedInUser(req);
        pa.setSubmission(submission);
        pa.addStudent(student);
        persistenceImplementation.merge(pa);
        resp.sendRedirect("/assignments");
    }
}