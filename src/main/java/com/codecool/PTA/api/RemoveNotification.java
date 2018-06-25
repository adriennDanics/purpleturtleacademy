package com.codecool.PTA.api;

import com.codecool.PTA.controller.AbstractController;
import com.codecool.PTA.persistence.PersistenceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveNotification extends AbstractController {

    private PersistenceImplementation persistenceImplementation;

    public RemoveNotification(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.removeAttribute("newRequest");
        AbstractController.setFirstNotification(false);
    }
}
