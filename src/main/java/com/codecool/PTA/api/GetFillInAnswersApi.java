package com.codecool.PTA.api;

import com.codecool.PTA.controller.AbstractController;
import com.codecool.PTA.model.quest.FillInAnswer;
import com.codecool.PTA.model.quest.FillInTheBlank;
import com.codecool.PTA.service.FillInAnswerService;
import com.codecool.PTA.service.FillInTheBlankService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GetFillInAnswersApi extends AbstractController {

    @Autowired
    private FillInAnswerService fillInAnswerService;

    @Autowired
    private FillInTheBlankService fillInTheBlankService;


    @GetMapping("/fill_in_answers")
    public ResponseEntity<JSONObject> getFillInAnswersForQuestion(@PathVariable Long id) {
        FillInTheBlank fillInTheBlank = fillInTheBlankService.findById(id);

        Map<String, String> answers = new HashMap<>();
        List<FillInAnswer> fillInAnswers = fillInAnswerService.getAnswersForQuestion(fillInTheBlank);
        for (int i = 0; i < fillInAnswers.size(); i++) {
            answers.put("answer" + i, fillInAnswers.get(i).getAnswer());
        }
        JSONObject response = new JSONObject(answers);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        long id = Long.valueOf(req.getParameter("id"));
//
//        //TODO: error handling
//        List<FillInAnswer> answers = persistenceImplementation.findFillInAnswersForQuestion(id);
//
//        JSONObject answerJSON = new JSONObject();
//
//        for (int i = 0; i < answers.size(); i++) {
//            answerJSON.put("answer" + i, answers.get(i).getAnswer());
//        }
//
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//        resp.getWriter().write(answerJSON.toString());
//        resp.getWriter().flush();
//    }
}
