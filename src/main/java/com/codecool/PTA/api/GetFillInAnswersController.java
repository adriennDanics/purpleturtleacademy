package com.codecool.PTA.api;

import com.codecool.PTA.controller.AbstractController;
import com.codecool.PTA.persistence.PersistenceImplementation;
import com.codecool.PTA.model.quest.FillInAnswer;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetFillInAnswersController extends AbstractController {
    
    private PersistenceImplementation persistenceImplementation;

    public GetFillInAnswersController(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));

        //TODO: error handling
        List<FillInAnswer> answers = persistenceImplementation.findFillInAnswersForQuestion(id);

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
