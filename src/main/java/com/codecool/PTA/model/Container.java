package com.codecool.PTA.model;

import com.codecool.PTA.JSON.GetFillInAnswersController;
import com.codecool.PTA.JSON.SendCourseInfo;
import com.codecool.PTA.config.AssignmentConfig;
import com.codecool.PTA.controller.*;
import com.codecool.PTA.persistence.PersistenceImplementation;

import javax.servlet.ServletContextEvent;

public class Container {

    public void createObjects(ServletContextEvent sce) {
        PersistenceImplementation persistenceImplementation = new PersistenceImplementation();
        new AssignmentConfig(persistenceImplementation);
        sce.getServletContext().addServlet("loginController", new LoginController(persistenceImplementation)).addMapping("/login");
        sce.getServletContext().addServlet("assignmentController", new AssignmentController(persistenceImplementation)).addMapping("/assignments");
        sce.getServletContext().addServlet("coursesController", new CoursesController(persistenceImplementation)).addMapping("/courses");
        sce.getServletContext().addServlet("fillTheBlanksController", new FillTheBlanksController(persistenceImplementation)).addMapping("/fill");
        sce.getServletContext().addServlet("indexController", new IndexController(persistenceImplementation)).addMapping("", "/index");
        sce.getServletContext().addServlet("PAController", new PAController(persistenceImplementation)).addMapping("/pa");
        sce.getServletContext().addServlet("quizController", new QuizController(persistenceImplementation)).addMapping("/quiz");
        sce.getServletContext().addServlet("getFillInAnswersController", new GetFillInAnswersController(persistenceImplementation)).addMapping("/fill_in_answers");
        sce.getServletContext().addServlet("registrationController", new RegistrationController(persistenceImplementation)).addMapping("/registration");
        sce.getServletContext().addServlet("sendCourseInfo", new SendCourseInfo(persistenceImplementation)).addMapping("/courseinfo");
        sce.getServletContext().addServlet("certificateController", new CertificateController()).addMapping("/profile/certificate");
        sce.getServletContext().addServlet("logoutController", new LogoutController()).addMapping("/logout");
        sce.getServletContext().addServlet("profileController", new ProfileController()).addMapping("/profile");
    }


}
