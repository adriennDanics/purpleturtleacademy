package com.codecool.PTA.config;

import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.service.StudentService;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    StudentService studentService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        Student student = studentService.findByUsername(username);
        long id = student.getId();
        long xp = student.getXp();
        Level level = student.getLevel();


        HttpSession session = request.getSession();
        session.setAttribute("student", student);
        session.setAttribute("id", id);
        session.setAttribute("xp", xp);
        session.setAttribute("level", level);

        response.sendRedirect("/");
    }
}
