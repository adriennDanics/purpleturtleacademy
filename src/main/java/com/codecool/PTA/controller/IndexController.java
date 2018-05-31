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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"", "/index"})
public class IndexController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkLogin(req)){
            WebContext context = new WebContext(req, resp, req.getServletContext());
            HttpSession session = req.getSession();
            Student student = (Student) session.getAttribute("student");

            Course course = PersistenceImplementation.getInstance().findCourseById(student.getCourse().getId());
            context.setVariable("course", course);
            context.setVariable("student", student);
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("index/index.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/login");
        }
    }
}
