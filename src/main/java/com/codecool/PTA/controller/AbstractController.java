package com.codecool.PTA.controller;

import com.codecool.PTA.model.user.Student;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractController {

    public static void setFirstNotification(boolean firstNotification) {
        AbstractController.firstNotification = firstNotification;
    }

    static boolean firstNotification = true;

    boolean checkLogin(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        return !(session.getAttribute("student") == null);
    }

    Student getLoggedInUser() {
        return (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    boolean isNewFriendRequest(HttpServletRequest req) {
        HttpSession session = req.getSession();

        Student student = (Student) session.getAttribute("student");
        if(student.getTaggedByOthers().size() != 0) {
            if(firstNotification) {
                session.setAttribute("newRequest", "new");
            }
        }
        return (student.getTaggedByOthers().size() != 0);
    }
}
