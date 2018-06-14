package com.codecool.PTA.controller;

import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.model.quest.Kata;
import com.codecool.PTA.persistence.PersistenceImplementation;
import com.codecool.PTA.model.quest.PA;
import com.codecool.PTA.model.quest.QuizQuestion;
import com.codecool.PTA.model.user.Student;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentController extends AbstractController {
    
    private PersistenceImplementation persistenceImplementation;

    public AssignmentController(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkLogin(req)) {
            WebContext context = new WebContext(req, resp, req.getServletContext());
            List<QuizQuestion> quizQuestions = persistenceImplementation.findAllQuizQuestion();
            context.setVariable("quizQuestions", quizQuestions);
            List<Kata> kataList = persistenceImplementation.findAllKatas();
            context.setVariable("kataList", kataList);
            List<PA> tempList = persistenceImplementation.findAllPaAssignments();
            List<PA> paList = new ArrayList();
            for (PA pa:tempList) {
                if (pa.isItTemplate){
                    paList.add(pa);
                }
            }
            context.setVariable("paList", paList);
            Student student = (Student) getLoggedInUser(req);
            context.setVariable("student", student);

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("assignments/assignments.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/login");
        }
    }
}
