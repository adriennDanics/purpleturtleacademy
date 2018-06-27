package com.codecool.PTA.helper;

import com.codecool.PTA.model.quest.Assignment;
import com.codecool.PTA.model.quest.FillInTheBlank;
import com.codecool.PTA.model.quest.QuizQuestion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RandomizeAssignment {
    private static List<Assignment> randomAssignments = new ArrayList<>();

    public Assignment makeRandomList(List<QuizQuestion> quizzes, List<FillInTheBlank> fillInTheBlank){
        randomAssignments.clear();
        randomAssignments.addAll(fillInTheBlank);
        randomAssignments.addAll(quizzes);
        Collections.shuffle(randomAssignments);
        return randomAssignments.get(0);
    }
}
