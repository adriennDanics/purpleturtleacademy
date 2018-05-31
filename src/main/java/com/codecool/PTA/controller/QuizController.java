package com.codecool.PTA.controller;

import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.persistence.PersistenceImplementation;
import com.codecool.PTA.quest.QuizQuestion;
import com.codecool.PTA.user.Student;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/quiz"})
public class QuizController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkLogin(req)) {
            long id = Long.valueOf(req.getParameter("id"));

            WebContext context = new WebContext(req, resp, req.getServletContext());
            QuizQuestion question = PersistenceImplementation.getInstance().findQuizQuestionById(id);
            HttpSession session = req.getSession();
            Student student = (Student) session.getAttribute("student");
            context.setVariable("student", student);
            context.setVariable("question", question);

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("quiz/quizzes.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/login");
        }
    }
}