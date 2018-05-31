package com.codecool.PTA.config;


import com.codecool.PTA.persistence.PersistenceImplementation;
import com.codecool.PTA.quest.CourseType;
import com.codecool.PTA.quest.QuizQuestion;
import com.codecool.PTA.user.Level;

import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AssignmentConfig {

    private ArrayList<QuizQuestion> questionList = new ArrayList();

    private void fillData(){
        Map<String, Boolean> answers1 = new HashMap<>();
        answers1.put("str.replace", true);
        answers1.put("str.replace()", false);
        answers1.put("str.replace(old, new[, count])", false);
        answers1.put("str.replace(old, new)", false);
        QuizQuestion quizQuestion1 = new QuizQuestion("What is the built-in function for replacing a substring in a string?", Level.BEGINNER, CourseType.Python, answers1);
        questionList.add(quizQuestion1);


        Map<String, Boolean> answers2 = new HashMap<>();
        answers2.put("str.upper()", false);
        answers2.put("str.upper", true);
        answers2.put("str.toUpperCase()", false);
        QuizQuestion quizQuestion2 = new QuizQuestion("What is the built-in function for converting a string to uppercase?", Level.BEGINNER, CourseType.Python, answers2);
        questionList.add(quizQuestion2);


        Map<String, Boolean> answers3 = new HashMap<>();
        answers3.put("sqrt(x)", false);
        answers3.put("math.sqrt()", false);
        answers3.put("math.sqrt(number)", false);
        answers3.put("math.sqrt(x)", true);
        QuizQuestion quizQuestion3 = new QuizQuestion("What is the built-in math function for calculating the square root of a number if my number is named \"x\"?", Level.BEGINNER, CourseType.Python, answers3);
        questionList.add(quizQuestion3);

        Map<String, Boolean> answers4 = new HashMap<>();
        answers4.put("array.insert()", false);
        answers4.put("array.insert", false);
        answers4.put("array.insert(i, x)", true);
        answers4.put("array.insert(position, x)", false);
        answers4.put("array.insert(i,x)", false);
        QuizQuestion quizQuestion4 = new QuizQuestion("What is the built-in function for inserting a new item with value x into a certain position in an array?", Level.BEGINNER, CourseType.Python, answers4);
        questionList.add(quizQuestion4);

        Map<String, Boolean> answers5 = new HashMap<>();
        answers5.put("curses.echo()", true);
        answers5.put("curses.echo", false);
        QuizQuestion quizQuestion5 = new QuizQuestion("What is the built-in function for entering echo mode in curses?", Level.BEGINNER, CourseType.Python, answers5);
        questionList.add(quizQuestion5);
    }

    private void fillDB(){
        for (QuizQuestion question:questionList) {
            PersistenceImplementation.getInstance().persist(question);
        }
    }

}
