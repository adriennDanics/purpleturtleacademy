package com.codecool.PTA.JSON;

import com.codecool.PTA.controller.AbstractController;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.persistence.PersistenceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HandleFriendRequests extends AbstractController {

    private PersistenceImplementation persistenceImplementation;

    public HandleFriendRequests(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requester = req.getParameter("requesterId");
        String requested = req.getParameter("requestedId");

        Student requesterStudent = persistenceImplementation.findStudentById((Long.valueOf(requester)));
        Student requestedStudent = persistenceImplementation.findStudentById((Long.valueOf(requested)));

        requesterStudent.addToPendingFriends(requestedStudent);
        requestedStudent.addToTaggedByOthers(requesterStudent);

        persistenceImplementation.merge(requesterStudent);
        persistenceImplementation.merge(requestedStudent);

    }
}
