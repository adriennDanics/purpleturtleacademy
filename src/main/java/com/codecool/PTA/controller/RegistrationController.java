package com.codecool.PTA.controller;

import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.helper.Hash;
import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.user.GenderEnum;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.persistence.PersistenceImplementation;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationController extends AbstractController {

    private PersistenceImplementation persistenceImplementation;
    private Hash hash;

    public RegistrationController(PersistenceImplementation persistenceImplementation, Hash hash) {
        this.persistenceImplementation = persistenceImplementation;
        this.hash = hash;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        engine.process("registration/registration.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (passwordsMatch(req)) {
            Student student = createNewlyRegisteredStudent(req);
            session.setAttribute("student", student);
            persistenceImplementation.persist(student);
            clearPasswordNotMatchFromSession(session);
            resp.sendRedirect("");
        } else {
            addPasswordNotMatchToSession(session);
            resp.sendRedirect("/registration");
        }
    }

    private void addPasswordNotMatchToSession(HttpSession session) {
        session.setAttribute("passwordNotMatch", "The passwords you entered are not matching!");
    }

    private void clearPasswordNotMatchFromSession(HttpSession session) {
        if (session.getAttribute("passwordNotMatch") != null) {
            session.removeAttribute("passwordNotMatch");
        }
    }

    private boolean passwordsMatch(HttpServletRequest req) {
        String password = req.getParameter("password");
        String passwordConfirm = req.getParameter("password_confirm");

        return password.equals(passwordConfirm);
    }

    GenderEnum translateGender(String genderChecked) {
        GenderEnum gender;
        if (genderChecked.equals("female")) {
            gender = GenderEnum.FEMALE;
        } else if (genderChecked.equals("male")) {
            gender = GenderEnum.MALE;
        } else {
            gender = GenderEnum.OTHER;
        }
        return gender;
    }

    public void setPersistenceImplementation(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    private Student createNewlyRegisteredStudent(HttpServletRequest req) {
        String hashedPassword = hash.hashPassword(req.getParameter("password"));
        Course course = persistenceImplementation.findCourseByName(CourseType.ORIENTATION);
        GenderEnum gender = translateGender(req.getParameter("gender"));

        return new Student(
                req.getParameter("username"),
                hashedPassword,
                req.getParameter("first_name"),
                req.getParameter("last_name"),
                req.getParameter("email"),
                course,
                gender
        );
    }

}
