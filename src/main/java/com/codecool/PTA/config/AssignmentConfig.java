package com.codecool.PTA.config;

import com.codecool.PTA.persistence.PersistenceImplementation;
import com.codecool.PTA.quest.CourseType;
import com.codecool.PTA.quest.FillInAnswer;
import com.codecool.PTA.quest.FillInTheBlank;
import com.codecool.PTA.quest.PA;
import com.codecool.PTA.quest.QuizQuestion;
import com.codecool.PTA.user.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssignmentConfig {

    private List<QuizQuestion> questionList = new ArrayList<>();
    private List<PA> paList = new ArrayList<>();

    private void fillData() {
        fillQuizQuestions();
        fillPAs();
    }

    private void fillQuizQuestions() {
        Map<String, Boolean> answers1 = new HashMap<>();
        answers1.put("str.replace", true);
        answers1.put("str.replace()", false);
        answers1.put("str.replace(old, new[, count])", false);
        answers1.put("str.replace(old, new)", false);
        QuizQuestion quizQuestion1 = new QuizQuestion("What is the built-in function for replacing a substring in a string?", "Replacing substrings", Level.BEGINNER, CourseType.Python, answers1);
        questionList.add(quizQuestion1);

        Map<String, Boolean> answers2 = new HashMap<>();
        answers2.put("str.upper()", false);
        answers2.put("str.upper", true);
        answers2.put("str.toUpperCase()", false);
        QuizQuestion quizQuestion2 = new QuizQuestion("What is the built-in function for converting a string to uppercase?", "Converting to uppercase", Level.BEGINNER, CourseType.Python, answers2);
        questionList.add(quizQuestion2);

        Map<String, Boolean> answers3 = new HashMap<>();
        answers3.put("sqrt(x)", false);
        answers3.put("math.sqrt()", false);
        answers3.put("math.sqrt(number)", false);
        answers3.put("math.sqrt(x)", true);
        QuizQuestion quizQuestion3 = new QuizQuestion("What is the built-in math function for calculating the square root of a number if my number is named \"x\"?", "Calculating square root", Level.BEGINNER, CourseType.Python, answers3);
        questionList.add(quizQuestion3);

        Map<String, Boolean> answers4 = new HashMap<>();
        answers4.put("array.insert()", false);
        answers4.put("array.insert", false);
        answers4.put("array.insert(i, x)", true);
        answers4.put("array.insert(position, x)", false);
        answers4.put("array.insert(i,x)", false);
        QuizQuestion quizQuestion4 = new QuizQuestion("What is the built-in function for inserting a new item with value x into a certain position in an array?", "Inserting into an array", Level.BEGINNER, CourseType.Python, answers4);
        questionList.add(quizQuestion4);

        Map<String, Boolean> answers5 = new HashMap<>();
        answers5.put("curses.echo()", true);
        answers5.put("curses.echo", false);
        QuizQuestion quizQuestion5 = new QuizQuestion("What is the built-in function for entering echo mode in curses?", "Entering echo mode", Level.BEGINNER, CourseType.Python, answers5);
        questionList.add(quizQuestion5);
    }

    private void fillPAs() {
        PA pa = new PA(Level.BEGINNER, CourseType.Python, "Simple calculator",
                "Write a calculator script, that waits for the user to enter a number, " +
                        "then a sign (plus, minus, multiplication and division), then a number again. " +
                        "After the 2nd number input, the script should calculate the addition or subtraction and print it out. " +
                        "Then the program should run again with asking for the first number. " +
                        "The script should exit when the user enters a letter instead of a number. " +
                        "Submit your python script file.");
        paList.add(pa);

        PA pa2 = new PA(Level.INTERMEDIATE, CourseType.Python, "Codewars assignment",
                "You have to collect at least 10 points by completing a subset of the katas below. We count point as follows: " +
                        "8 kyu kata scores 1 point, " +
                        "7 kyu kata scores 2 points and " +
                        "6 kyu kata scores 4 points. " +
                        "For practice and getting more familiar with Python tricks we suggest you to check the solutions " +
                        "(after completing a kata), understand the top ranked ones and rewrite your code from scratch based on the new insight.");
        paList.add(pa2);

        PA pa3 = new PA(Level.ADVANCED, CourseType.Java, "Practice JPA",
                "This assignment is a step by step introduction to the most widely used JPA annotations. " +
                        "The instructions are attached in the README.md file. " +
                        "It starts with a code you can run after some basic setup (step 1). " +
                        "Check the configurations in persistence.xml! " +
                        "The following steps hint the annotation to use or to modify to get to the expected result. " +
                        "Read about those annotations in the documentation or in any other resource, and play with the options, " +
                        "always checking the consequences in the database and in the state of the in-memory objects!");
        paList.add(pa3);
    }

//    public void fillDB() {
//        fillData();
//        for (QuizQuestion question : questionList) {
//            PersistenceImplementation.getInstance().persist(question);
//        }
//        for (PA pa : paList) {
//            PersistenceImplementation.getInstance().persist(pa);
//        }
//    }

    public void fillFillInTheBlankDb() {
        FillInTheBlank toFill1 = new FillInTheBlank(Level.BEGINNER,
                                                    CourseType.Python,
                                                    "Please fill in the blank to print!",
                                                    "<input type=\"text\" class=\"answer\" size=\"5\" >(\"Hello World!\")");

        FillInAnswer answer1 = new FillInAnswer("print", toFill1);

        PersistenceImplementation.getInstance().persist(toFill1);
        PersistenceImplementation.getInstance().persist(answer1);

        FillInTheBlank toFill2 = new FillInTheBlank(Level.BEGINNER,
                                                    CourseType.Python,
                                                    "Please fill in the blank to print: Hello World!",
                                                    "<input type=\"text\" class=\"answer\" size=\"5\" >(\"Hello  + \" <input type=\"text\" class=\"answer\" size=\"5\" >)");

        FillInAnswer answer2 = new FillInAnswer("print", toFill2);
        FillInAnswer answer3 = new FillInAnswer(" World!\"", toFill2);

        PersistenceImplementation.getInstance().persist(toFill2);
        PersistenceImplementation.getInstance().persist(answer2);
        PersistenceImplementation.getInstance().persist(answer3);


    }



}
