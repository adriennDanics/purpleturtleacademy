package com.codecool.PTA.controller;

import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.persistence.PersistenceImplementation;
import com.codecool.PTA.model.quest.QuizQuestion;
import com.codecool.PTA.model.user.Student;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class QuizController extends AbstractController {

    private PersistenceImplementation persistenceImplementation;

    public QuizController(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkLogin(req)) {
            isNewFriendRequest(req);
            long id = Long.valueOf(req.getParameter("id"));

            WebContext context = new WebContext(req, resp, req.getServletContext());
            QuizQuestion question = persistenceImplementation.findQuizQuestionById(id);
            Student student = (Student) getLoggedInUser(req);
            context.setVariable("student", student);
            context.setVariable("question", question);

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("quiz/quizzes.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = (Student) getLoggedInUser(req);

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append('\n');
        }

        reader.close();

        long xp = Long.valueOf(sb.toString().trim());
        student.setXp(xp);
        persistenceImplementation.merge(student);
    }
}