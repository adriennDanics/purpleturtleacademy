package com.codecool.PTA.controller;

import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.persistence.PersistenceImplementation;
import com.codecool.PTA.model.user.Student;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexController extends AbstractController {
    
    private PersistenceImplementation persistenceImplementation;

    public IndexController(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkLogin(req)){
            WebContext context = new WebContext(req, resp, req.getServletContext());
            Student student = (Student) getLoggedInUser(req);
            isNewFriendRequest(req);

            Course course = persistenceImplementation.findCourseById(student.getCourse().getId());
            context.setVariable("course", course);
            context.setVariable("student", student);
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("index/index.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/login");
        }
    }
}
