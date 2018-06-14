package com.codecool.PTA.controller;

import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.Kata;
import com.codecool.PTA.model.quest.PA;
import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.persistence.PersistenceImplementation;
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
            Student student = (Student) getLoggedInUser(req);
            CourseType courseName = student.getCourse().getName();
            Level levelName = student.getLevel();
            isNewFriendRequest(req);
            WebContext context = new WebContext(req, resp, req.getServletContext());
            try{
                List<Kata> tempKataList = persistenceImplementation.findAllKatas(courseName, levelName);
                List<Kata> kataList = new ArrayList<>();
                for (Kata kata:tempKataList) {
                    if (kata.isItTemplate){
                        kataList.add(kata);
                    }
                }
                context.setVariable("kataList", kataList);

            } catch (IOException ex){
                context.setVariable("kataList", null);
            }

            try {
                List<PA> tempPaList = persistenceImplementation.findAllPaAssignments(courseName, levelName);
                List<PA> paList = new ArrayList();
                for (PA pa:tempPaList) {
                    if (pa.isItTemplate){
                        paList.add(pa);
                    }
                }
                context.setVariable("paList", paList);
            } catch (IOException ex){
                context.setVariable("paList", null);
            }

            context.setVariable("student", student);

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("assignments/assignments.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/login");
        }
    }
}
