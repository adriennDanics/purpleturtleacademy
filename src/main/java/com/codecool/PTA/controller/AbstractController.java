package com.codecool.PTA.controller;

import com.codecool.PTA.model.user.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractController extends HttpServlet {

    boolean checkLogin(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        return !(session.getAttribute("student") == null);
    }

    User getLoggedInUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return (User) session.getAttribute("student");
    }
}
