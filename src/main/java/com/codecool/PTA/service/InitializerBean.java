package com.codecool.PTA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializerBean {

    @Autowired
    private ExampleEntityCreator exampleEntityCreator;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private FillInAnswerService fillInAnswerService;
    @Autowired
    private FillInTheBlankService fillInTheBlankService;
    @Autowired
    private QuizQuestionService quizQuestionService;
    @Autowired
    private KataService kataService;
    @Autowired
    private PAService paService;

    public InitializerBean() {
    }

    @PostConstruct
    private void fillDB() {
        exampleEntityCreator.fillData();
        exampleEntityCreator.courseList.forEach(course -> courseService.save(course));
        exampleEntityCreator.studentList.forEach(student -> studentService.save(student));
        exampleEntityCreator.fillInTheBlankList.forEach(fillInTheBlank -> fillInTheBlankService.save(fillInTheBlank));
        exampleEntityCreator.fillInAnswerList.forEach(fillInAnswer -> fillInAnswerService.save(fillInAnswer));
        exampleEntityCreator.questionList.forEach(question -> quizQuestionService.save(question));
        exampleEntityCreator.kataList.forEach(kata -> kataService.save(kata));
        exampleEntityCreator.paList.forEach(pa -> paService.save(pa));
    }

}

