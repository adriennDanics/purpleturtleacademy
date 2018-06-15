package com.codecool.PTA.controller;

import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.persistence.PersistenceImplementation;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileController extends AbstractController {

    private PersistenceImplementation persistenceImplementation;

    public ProfileController(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkLogin(req)) {
            isNewFriendRequest(req);
            WebContext context = new WebContext(req, resp, req.getServletContext());
            Student student = (Student) getLoggedInUser(req);
            context.setVariable("student", student);

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("profile/profile.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = (Student) getLoggedInUser(req);
        String newImage = req.getParameter("new-image");

        if(!newImage.equals("")){
            student.setImage(newImage);
        } else {
            student.reSetDefaultImage();
        }

        persistenceImplementation.merge(student);
        resp.sendRedirect("/profile");
    }
}
