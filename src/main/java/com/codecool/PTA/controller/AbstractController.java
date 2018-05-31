package com.codecool.PTA.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractController extends HttpServlet {

    boolean checkLogin(HttpServletRequest req){
        HttpSession session = req.getSession(true);
        if(session.getAttribute("student") == null){
            return false;
        }
        return true;
    }
}
