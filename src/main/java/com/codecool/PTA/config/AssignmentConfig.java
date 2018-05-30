package com.codecool.PTA.config;


import com.codecool.PTA.quest.CourseType;
import com.codecool.PTA.quest.QuizQuestion;
import com.codecool.PTA.user.Level;
import com.sun.javafx.collections.MappingChange;

import java.util.HashMap;
import java.util.Map;

public class AssignmentConfig {

    public AssignmentConfig() {

        Map<String, Boolean> answers1 = new HashMap<>();
        answers1.put("str.replace", true);
        answers1.put("str.replace()", false);
        answers1.put("str.replace(old, new[, count])", false);
        answers1.put("str.replace(old, new)", false);
        QuizQuestion quizQuestion1 = new QuizQuestion("What is the built-in function for replacing a substring in a string?", Level.BEGINNER, CourseType.Python, answers1);


        Map<String, Boolean> answers2 = new HashMap<>();
        answers1.put("str.replace", true);
        answers1.put("str.replace()", false);
        answers1.put("str.replace(old, new[, count])", false);
        answers1.put("str.replace(old, new)", false);
        QuizQuestion quizQuestion2 = new QuizQuestion("What is the built-in function for replacing a substring in a string?", Level.BEGINNER, CourseType.Python, answers2);


        Map<String, Boolean> answers3 = new HashMap<>();
        answers1.put("str.replace", true);
        answers1.put("str.replace()", false);
        answers1.put("str.replace(old, new[, count])", false);
        answers1.put("str.replace(old, new)", false);
        QuizQuestion quizQuestion3 = new QuizQuestion("What is the built-in function for replacing a substring in a string?", Level.BEGINNER, CourseType.Python, answers3);


        Map<String, Boolean> answers4 = new HashMap<>();
        answers1.put("str.replace", true);
        answers1.put("str.replace()", false);
        answers1.put("str.replace(old, new[, count])", false);
        answers1.put("str.replace(old, new)", false);
        QuizQuestion quizQuestion4 = new QuizQuestion("What is the built-in function for replacing a substring in a string?", Level.BEGINNER, CourseType.Python, answers4);
    }
}
