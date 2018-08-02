package com.codecool.PTA.helper;

import com.codecool.PTA.model.quest.Assignment;
import com.codecool.PTA.model.quest.FillInTheBlank;
import com.codecool.PTA.model.quest.QuizQuestion;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.service.FillInTheBlankService;
import com.codecool.PTA.service.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RandomizeAssignment {

    @Autowired
    private FillInTheBlankService fillInTheBlankService;

    @Autowired
    private QuizQuestionService quizQuestionService;

    private static List<Assignment> randomAssignments = new ArrayList<>();

    public List<Assignment> makeRandomList(Student student){
        List<FillInTheBlank> fillInQuestionList = fillInTheBlankService.findFillInTheBlanksByCourseTypeAndLevel(student.getCourse().getName(), student.getLevel());
        List<QuizQuestion> quizQuestionList = quizQuestionService.findFillInTheBlanksByCourseTypeAndLevel(student.getCourse().getName(), student.getLevel());
        randomAssignments.addAll(fillInQuestionList);
        randomAssignments.addAll(quizQuestionList);
        Collections.shuffle(randomAssignments);
        return randomAssignments.subList(0,5);
    }
}
