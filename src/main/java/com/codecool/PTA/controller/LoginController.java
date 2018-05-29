package com.codecool.PTA.controller;

import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.user.Student;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import java.text.ParseException;


@WebServlet(urlPatterns = {"/"})
public class LoginController extends AbstractController {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            WebContext context = new WebContext(req, resp, req.getServletContext());

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("index.html", context, resp.getWriter());

        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ptadb");
        EntityManager em = emf.createEntityManager();

        Query getAllStudents = em.createQuery("SELECT stud FROM Student stud");

        List<Student> studentList = getAllStudents.getResultList();
        for (Student student : studentList) {
            if(username.equals(student.getUsername())) {
                if(password.equals(student.getHashedPassword())) {//Hash.isPasswordCorrect(password, customer.getHashedPassword())) {
                    session.setAttribute("student", student);
                    resp.sendRedirect("/");
                }
            } else {
                resp.sendRedirect("/");
            }
        }
    }
}
