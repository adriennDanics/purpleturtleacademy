package com.codecool.PTA.controller;


import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.course.Course;
import com.codecool.PTA.persistence.PersistenceImplementation;
import com.codecool.PTA.user.Student;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/courses"})
public class CoursesController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkLogin(req)) {
            WebContext context = new WebContext(req, resp, req.getServletContext());
            List<Course> courses = PersistenceImplementation.getInstance().findAllCourses();
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
