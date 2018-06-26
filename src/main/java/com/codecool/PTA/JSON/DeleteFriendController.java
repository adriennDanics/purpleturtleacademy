package com.codecool.PTA.JSON;

import com.codecool.PTA.controller.AbstractController;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.persistence.PersistenceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteFriendController extends AbstractController {

    private PersistenceImplementation persistenceImplementation;

    public DeleteFriendController(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long deletingStudentId = Long.valueOf(req.getParameter("deletingStudent"));
        long studentToDeleteId = Long.valueOf(req.getParameter("studentToDelete"));

        Student deletingStudent = persistenceImplementation.findStudentById(deletingStudentId);
        Student studentToDelete = persistenceImplementation.findStudentById(studentToDeleteId);

        deletingStudent.removeFromFriends(studentToDelete);
        studentToDelete.removeFromFriends(deletingStudent);

        persistenceImplementation.merge(deletingStudent);
        persistenceImplementation.merge(studentToDelete);

        resp.sendRedirect("/friends");
    }
}
