package com.codecool.PTA.model;

import com.codecool.PTA.JSON.*;
import com.codecool.PTA.config.AssignmentConfig;
import com.codecool.PTA.controller.*;
import com.codecool.PTA.helper.Hash;
import com.codecool.PTA.persistence.PersistenceImplementation;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

public class Container {

    public void createObjects(ServletContextEvent sce) {
        PersistenceImplementation persistenceImplementation = new PersistenceImplementation();
        Hash hash = new Hash();
        new AssignmentConfig(persistenceImplementation);
        ServletContext servletContext = sce.getServletContext();
        servletContext.addServlet("loginController", new LoginController(persistenceImplementation, hash)).addMapping("/login");
        servletContext.addServlet("assignmentController", new AssignmentController(persistenceImplementation)).addMapping("/assignments");
        servletContext.addServlet("coursesController", new CoursesController(persistenceImplementation)).addMapping("/courses");
        servletContext.addServlet("fillTheBlanksController", new FillTheBlanksController(persistenceImplementation)).addMapping("/fill");
        servletContext.addServlet("indexController", new IndexController(persistenceImplementation)).addMapping("", "/index");
        servletContext.addServlet("PAController", new PAController(persistenceImplementation)).addMapping("/pa");
        servletContext.addServlet("quizController", new QuizController(persistenceImplementation)).addMapping("/quiz");
        servletContext.addServlet("getFillInAnswersController", new GetFillInAnswersController(persistenceImplementation)).addMapping("/fill_in_answers");
        servletContext.addServlet("registrationController", new RegistrationController(persistenceImplementation, hash)).addMapping("/registration");
        servletContext.addServlet("certificateController", new CertificateController()).addMapping("/profile/certificate");
        servletContext.addServlet("logoutController", new LogoutController()).addMapping("/logout");
        servletContext.addServlet("profileController", new ProfileController(persistenceImplementation)).addMapping("/profile");
        servletContext.addServlet("kataController", new KataController(persistenceImplementation)).addMapping("/kata");
        servletContext.addServlet("sendCourseInfo", new SendCourseInfo(persistenceImplementation)).addMapping("/courseinfo");
        servletContext.addServlet("receiveNewName", new ReceiveNewName(persistenceImplementation)).addMapping("/profile/newname");
        servletContext.addServlet("receiveNewPassword", new ReceiveNewPassword(persistenceImplementation, hash)).addMapping("/profile/newpassword");
        servletContext.addServlet("listUsersController", new ListUsersController(persistenceImplementation)).addMapping("/list-users");
        servletContext.addServlet("handleFriendRequests", new HandleFriendRequests(persistenceImplementation)).addMapping("/save-friend-request");
        servletContext.addServlet("friendsController", new FriendsController(persistenceImplementation)).addMapping("/friends");
        servletContext.addServlet("friendRequestsController", new FriendRequestsController(persistenceImplementation)).addMapping("/friend-requests");
        servletContext.addServlet("acceptFriendRequest", new AcceptFriendRequest(persistenceImplementation)).addMapping("/accept-request");
        servletContext.addServlet("rejectRequestsController", new RejectFriendRequest(persistenceImplementation)).addMapping("/reject-request");

    }
    
}
