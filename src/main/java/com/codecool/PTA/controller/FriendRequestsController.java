package com.codecool.PTA.controller;

import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.persistence.PersistenceImplementation;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class FriendRequestsController extends AbstractController {

    private PersistenceImplementation persistenceImplementation;

    public FriendRequestsController(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(checkLogin(req)) {
            HttpSession session = req.getSession();
            Student student = (Student) session.getAttribute("student");

            Set<Student> friendRequests = student.getTaggedByOthers();

            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("student", student);
            context.setVariable("taggedBy", friendRequests);
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("friends/friendRequests.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/login");
        }
    }
}
