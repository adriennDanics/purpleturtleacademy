package com.codecool.PTA.controller;

import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.model.quest.QuizQuestion;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.persistence.PersistenceImplementation;
import org.json.JSONObject;
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
//TODO
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkLogin(req)) {
            checkForNewFriendRequest(req);
            long id = Long.valueOf(req.getParameter("id"));

            WebContext context = new WebContext(req, resp, req.getServletContext());
            QuizQuestion question = persistenceImplementation.findQuizQuestionById(id);
            Student student = (Student) getLoggedInUser(req);
            context.setVariable("student", student);
            context.setVariable("question", question);
            context.setVariable("left", req.getParameter("left"));

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

        JSONObject xpInfo = new JSONObject(sb.toString());

        long xp = Long.valueOf(xpInfo.get("xp").toString());
        student.setXp(xp);
        persistenceImplementation.merge(student);
    }
}