package com.codecool.PTA.controller;

import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.service.UserDetailsImp;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractController {

    protected final static String SUCCESS = "success";
    protected static boolean isFirstNotification = true;

    boolean checkLogin(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        return !(session.getAttribute("student") == null);
    }

    protected Student getLoggedInUser() {
        UserDetailsImp userDetailsImp = (UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetailsImp.getUser();
    }

    protected HttpSession getHttpSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }

    void checkForNewFriendRequest() {
        Student student = getLoggedInUser();
        final boolean hasBeenTagged = student.getTaggedByOthers().size() != 0;
        if (hasBeenTagged && isFirstNotification) {
            HttpSession session = getHttpSession();
            session.setAttribute("newRequest", "new");
        }
    }

}
