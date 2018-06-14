package com.codecool.PTA.JSON;

import com.codecool.PTA.controller.AbstractController;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.persistence.PersistenceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AcceptFriendRequest extends AbstractController {

    private PersistenceImplementation persistenceImplementation;

    public AcceptFriendRequest(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Student loggedInStudent = persistenceImplementation.findStudentById(Long.valueOf(req.getParameter("loggedInStudentId")));
        Student requesterStudent = persistenceImplementation.findStudentById(Long.valueOf(req.getParameter("requesterId")));

        loggedInStudent.addToFriends(requesterStudent);
        requesterStudent.addToFriends(loggedInStudent);

        requesterStudent.removeFromPendingFriends(loggedInStudent);
        loggedInStudent.removeFromTaggedByOthers(requesterStudent);

        persistenceImplementation.merge(loggedInStudent);
        persistenceImplementation.merge(requesterStudent);

    }
}
