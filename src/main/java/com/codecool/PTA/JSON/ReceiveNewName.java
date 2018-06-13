package com.codecool.PTA.JSON;

import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.persistence.PersistenceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

public class ReceiveNewName extends HttpServlet {

    private PersistenceImplementation persistenceImplementation;

    public ReceiveNewName(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Student student = (Student) session.getAttribute("student");

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append('\n');
        }
        reader.close();

        /*
         * Trailing quotes necessitated using substring for new username
         * the length of a string is surprisingly the actual length+1,
         * so there needs to be two "characters" chopped off from the end.
         */
        String newName = sb.toString().trim().substring(1, sb.length() - 2);
        student.setUsername(newName);
        persistenceImplementation.merge(student);
    }
}
