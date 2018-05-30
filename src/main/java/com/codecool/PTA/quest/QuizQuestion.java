package com.codecool.PTA.quest;

import com.codecool.PTA.user.Level;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class QuizQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    String question;
    String answer1;
    String answer2;
    String answer3;
    String answer4;

    Level level;

    CourseType courseType;


    private QuizQuestion(){}

    public QuizQuestion(String question) {
        this.question = question;
    }

}
