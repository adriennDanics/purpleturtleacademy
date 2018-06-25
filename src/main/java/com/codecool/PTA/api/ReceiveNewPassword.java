package com.codecool.PTA.api;

import com.codecool.PTA.helper.Hash;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.persistence.PersistenceImplementation;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

public class ReceiveNewPassword extends HttpServlet {

    private PersistenceImplementation persistenceImplementation;
    private Hash hash;

    public ReceiveNewPassword(PersistenceImplementation persistenceImplementation, Hash hash) {
        this.persistenceImplementation = persistenceImplementation;
        this.hash = hash;
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

        JSONObject passwords = new JSONObject(sb.toString());
        if(hash.isPasswordCorrect(passwords.get("old").toString(), student.getPassword())){
            String password = passwords.get("new").toString();
            student.setPassword(hash.hashPassword(password));
            persistenceImplementation.merge(student);
        } else {
            JSONObject denial = new JSONObject();
            denial.put("message", "Old password was wrong, change not possible");
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(denial.toString());
            resp.getWriter().flush();
        }

    }
}