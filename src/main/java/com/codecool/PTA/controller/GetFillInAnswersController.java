package com.codecool.PTA.controller;

import com.codecool.PTA.persistence.PersistenceImplementation;
import com.codecool.PTA.quest.FillInAnswer;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/fill_in_answers"})
public class GetFillInAnswersController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));

        List<FillInAnswer> answers = PersistenceImplementation.getInstance().findFillInAnswersForQuestion(id);

        JSONObject answerJSON = new JSONObject();

        for (int i = 0; i < answers.size(); i++) {
            answerJSON.put("answer" + i, answers.get(i).getAnswer());
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(answerJSON.toString());
        resp.getWriter().flush();
    }
}
