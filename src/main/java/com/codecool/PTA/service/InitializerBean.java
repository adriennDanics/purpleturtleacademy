package com.codecool.PTA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitializerBean {

    @Autowired
    private ExampleEntityCreator exampleEntityCreator;

    public InitializerBean(
            StudentService studentService,
            CourseService courseService,
            FillInAnswerService fillInAnswerService,
            FillInTheBlankService fillInTheBlankService,
            QuizQuestionService quizQuestionService,
            KataService kataService,
            PAService paService
    ) {
        exampleEntityCreator.fillData();
        exampleEntityCreator.studentList.forEach(student -> studentService.saveStudent(student));
        exampleEntityCreator.courseList.forEach(course -> courseService.saveCourse(course));
        exampleEntityCreator.fillInAnswerList.forEach(fillInAnswer-> fillInAnswerService.saveFillInAnswer(fillInAnswer));
        exampleEntityCreator.fillInTheBlankList.forEach(fillInTheBlank-> fillInTheBlankService.saveFillInTheBlank(fillInTheBlank));
        exampleEntityCreator.questionList.forEach(question -> quizQuestionService.saveQuizQuestion(question));
        exampleEntityCreator.kataList.forEach(kata -> kataService.saveKata(kata));
        exampleEntityCreator.paList.forEach(pa -> paService.savePa(pa));
    }

}

