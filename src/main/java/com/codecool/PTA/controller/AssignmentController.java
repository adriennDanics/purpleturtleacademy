package com.codecool.PTA.controller;

import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.course.Course;
import com.codecool.PTA.persistence.PersistenceImplementation;
import com.codecool.PTA.quest.Assignment;
import com.codecool.PTA.quest.PA;
import com.codecool.PTA.quest.QuizQuestion;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/assignments"})
public class AssignmentController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkLogin(req)) {
            WebContext context = new WebContext(req, resp, req.getServletContext());
            List<QuizQuestion> quizQuestions = PersistenceImplementation.getInstance().findAllQuizQuestion();
            context.setVariable("quizQuestions", quizQuestions);
            List<PA> paList = PersistenceImplementation.getInstance().findAllPaAssignments();
            context.setVariable("paList", paList);

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("assignments/assignments.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/login");
        }
    }
}
