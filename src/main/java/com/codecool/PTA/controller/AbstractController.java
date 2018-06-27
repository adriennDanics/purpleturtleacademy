package com.codecool.PTA.controller;

import com.codecool.PTA.model.user.Student;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractController {

    public static void setIsFirstNotification(boolean isFirstNotification) {
        AbstractController.isFirstNotification = isFirstNotification;
    }

    private static boolean isFirstNotification = true;

    boolean checkLogin(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        return !(session.getAttribute("student") == null);
    }

    protected Student getLoggedInUser() {
        return (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    void checkForNewFriendRequest() {
        Student student = getLoggedInUser();
        final boolean hasBeenTagged = student.getTaggedByOthers().size() != 0;
        if (hasBeenTagged && isFirstNotification) {
            HttpSession session = getHttpSession();
            session.setAttribute("newRequest", "new");
        }
    }

    private HttpSession getHttpSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }

}
