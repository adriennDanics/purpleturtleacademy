package com.codecool.PTA.controller;

import com.codecool.PTA.certificate.Certificate;
import com.codecool.PTA.config.TemplateEngineUtil;
import com.codecool.PTA.user.Student;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/profile/certificate"})
public class CertificateController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkLogin(req)){
            WebContext context = new WebContext(req, resp, req.getServletContext());
            Student student = (Student) getLoggedInUser(req);
            Certificate certificate = student.getCertificate();
            context.setVariable("student", student);
            context.setVariable("certificate", certificate);

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("profile/certificate.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/login");
        }
    }
}
