package com.codecool.PTA.controller;

import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.course.Course;
import com.codecool.PTA.course.CourseType;
import com.codecool.PTA.helper.Hash;
import com.codecool.PTA.persistence.PersistenceImplementation;
import com.codecool.PTA.user.GenderEnum;
import com.codecool.PTA.user.Student;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext context = new WebContext(req, resp, req.getServletContext());

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        engine.process("registration/registration.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String passwordConfirm = req.getParameter("password_confirm");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");

        HttpSession session = req.getSession();

        if(password.equals(passwordConfirm)) {
            Course course = PersistenceImplementation.getInstance().findCourseByName(CourseType.ORIENTATION);
            String hashedPassword = Hash.hashPassword(req.getParameter("password"));
            Student student = new Student(username, hashedPassword);
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setEmail(email);
            student.setCourse(course);
            student.setGender(translateGender(gender));
            session.setAttribute("student", student);
            if(session.getAttribute("passwordNotMatch") != null) {
                session.removeAttribute("passwordNotMatch");
            }
            PersistenceImplementation.getInstance().persist(student);
            resp.sendRedirect("");
        } else {
            session.setAttribute("passwordNotMatch", "The passwords you entered are not matching!");
            resp.sendRedirect("/registration");
        }
    }

    GenderEnum translateGender(String genderChecked){
        GenderEnum gender;
        if(genderChecked.equals("female")){
            gender = GenderEnum.FEMALE;
        } else if (genderChecked.equals("male")){
            gender = GenderEnum.MALE;
        } else {
            gender = GenderEnum.OTHER;
        }
        return gender;
    }
}
