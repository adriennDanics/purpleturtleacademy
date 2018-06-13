package com.codecool.PTA.JSON;

import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.persistence.PersistenceImplementation;
import com.codecool.PTA.model.user.Student;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SendCourseInfo extends HttpServlet {
    
    private PersistenceImplementation persistenceImplementation;

    public SendCourseInfo(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));
        //TODO: error handling
        Course course = persistenceImplementation.findCourseById(id);
        JSONObject item = new JSONObject();
        item.put("name", course.getName().toString());
        item.put("description", course.getDescription());
        item.put("id", course.getId());
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(item.toString());
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));
        //TODO: error handling
        Course course = persistenceImplementation.findCourseById(id);
        HttpSession session = req.getSession();
        Student student = (Student) session.getAttribute("student");
        student.setCourse(course);
        persistenceImplementation.merge(student);
    }
}