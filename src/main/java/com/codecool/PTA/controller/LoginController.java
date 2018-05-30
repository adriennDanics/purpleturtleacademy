package com.codecool.PTA.controller;

import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.helper.Hash;
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


@WebServlet(urlPatterns = {"/login"})
public class LoginController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext context = new WebContext(req, resp, req.getServletContext());

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        engine.process("login/login.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ptaPU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query getAllStudents = em.createQuery("SELECT stud FROM Student stud");
        List<Student> studentList = getAllStudents.getResultList();
        transaction.commit();
        em.close();



        System.out.println(studentList.size());
        for (Student student : studentList) {
            System.out.println(student.getUsername());
            if(username.equals(student.getUsername())) {
                if(Hash.isPasswordCorrect(password, student.getPassword())) {
                    session.setAttribute("student", student);
                    resp.sendRedirect("");
                }
            } else {
                resp.sendRedirect("");
            }
        }
    }
}
