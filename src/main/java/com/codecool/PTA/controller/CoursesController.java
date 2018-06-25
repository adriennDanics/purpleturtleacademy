package com.codecool.PTA.controller;


import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.persistence.PersistenceImplementation;
import com.codecool.PTA.model.user.Student;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CoursesController extends AbstractController {
    
    private PersistenceImplementation persistenceImplementation;

    public CoursesController(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    @GetMapping
    public String listCourses(Model model) {
        return "courses/courses";
    }
    //TODO
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        if(checkLogin(req)) {
            isNewFriendRequest(req);
            WebContext context = new WebContext(req, resp, req.getServletContext());
            List<Course> courses = persistenceImplementation.findAllCourses();
            Student student = (Student) getLoggedInUser(req);

            context.setVariable("student", student);
            context.setVariable("courses", courses);

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("courses/courses.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/login");
        }
    }
}
