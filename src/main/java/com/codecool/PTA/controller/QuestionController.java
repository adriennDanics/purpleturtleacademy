package com.codecool.PTA.controller;

import com.codecool.PTA.helper.RandomizeAssignment;
import com.codecool.PTA.model.quest.Assignment;
import com.codecool.PTA.model.quest.FillInTheBlank;
import com.codecool.PTA.model.quest.QuizQuestion;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.persistence.PersistenceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class QuestionController extends AbstractController {

    private PersistenceImplementation persistenceImplementation;
    private RandomizeAssignment randomizeAssignment;
    private static Set<Assignment> questionsCompleted = new TreeSet<>();

    public QuestionController(PersistenceImplementation persistenceImplementation, RandomizeAssignment randomAssignments) {
        this.persistenceImplementation = persistenceImplementation;
        this.randomizeAssignment = randomAssignments;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkLogin(req)){
            Integer numberLeft = Integer.valueOf(req.getParameter("id"));
            Student student = (Student) getLoggedInUser(req);

            List<FillInTheBlank> idList = persistenceImplementation.findAllFillInTheBlank(student.getCourse().getName(), student.getLevel());
            List<QuizQuestion> idListQuiz = persistenceImplementation.findAllQuizQuestion(student.getCourse().getName(), student.getLevel());

            Assignment currentAssignment = randomizeAssignment.makeRandomList(idListQuiz, idList);
            if(!questionsCompleted.isEmpty() && questionsCompleted.contains(currentAssignment)){
                while(questionsCompleted.contains(currentAssignment)) {
                    currentAssignment = randomizeAssignment.makeRandomList(idListQuiz, idList);
                }
            }
            if(numberLeft==0){
                questionsCompleted.clear();
                randomizeAssignment.makeRandomList(idListQuiz, idList);
            }

            if(currentAssignment.getClass() == FillInTheBlank.class){
                long next = currentAssignment.getId();
                questionsCompleted.add(currentAssignment);
                resp.sendRedirect("/fill?id=" + next + "&left=" + numberLeft);
            } else {
                long nextQuiz = currentAssignment.getId();
                questionsCompleted.add(currentAssignment);
                resp.sendRedirect("/quiz?id="+nextQuiz + "&left=" + numberLeft);
            }
        } else {
            resp.sendRedirect("/login");
        }
    }
}