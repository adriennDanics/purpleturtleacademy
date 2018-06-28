package com.codecool.PTA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitializerBean {

    @Autowired
    private ExampleEntityCreator exampleEntityCreator;

    public InitializerBean() {
    }

    public InitializerBean(
            StudentService studentService,
            CourseService courseService,
            FillInAnswerService fillInAnswerService,
            FillInTheBlankService fillInTheBlankService,
            QuizQuestionService quizQuestionService,
            KataService kataService,
            PAService paService
    ) {
        this();
        exampleEntityCreator.fillData();
        exampleEntityCreator.studentList.forEach(student -> studentService.save(student));
        exampleEntityCreator.courseList.forEach(course -> courseService.save(course));
        exampleEntityCreator.fillInAnswerList.forEach(fillInAnswer -> fillInAnswerService.save(fillInAnswer));
        exampleEntityCreator.fillInTheBlankList.forEach(fillInTheBlank -> fillInTheBlankService.save(fillInTheBlank));
        exampleEntityCreator.questionList.forEach(question -> quizQuestionService.save(question));
        exampleEntityCreator.kataList.forEach(kata -> kataService.save(kata));
        exampleEntityCreator.paList.forEach(pa -> paService.save(pa));
    }

}

