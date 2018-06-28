package com.codecool.PTA.controller;

import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.*;
import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.service.*;
import com.codecool.PTA.helper.RandomizeAssignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

//TODO
@Controller
public class AssignmentController extends AbstractController {

    @Autowired
    private KataService kataService;

    @Autowired
    private PAService paService;

    @Autowired
    private FillInTheBlankService fillInTheBlankService;

    @Autowired
    private QuizQuestionService quizQuestionService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private RandomizeAssignment randomizeAssignment;

    private static Set<Assignment> questionsCompleted = new TreeSet<>();

    @GetMapping("/assignments")
    public String listAssignments(Model model) {
        Student student = getLoggedInUser();
        CourseType courseName = student.getCourse().getName();
        Level levelName = student.getLevel();
        checkForNewFriendRequest();

        model.addAttribute("kataList", kataService.findKataTemplatesByCourseNameAndLevelName(courseName, levelName));
        model.addAttribute("paList", paService.findPaTemplatesByCourseNameAndLevelName(courseName, levelName));
        model.addAttribute("student", student);

        return "assignments/assignments";
    }

    @GetMapping("/fill/{id}/{left}/{xp}")
    public String displayFillAssignment(@PathVariable Long id, @PathVariable String left, @PathVariable Long xpChange, Model model) {
        checkForNewFriendRequest();
        model.addAttribute("student", getLoggedInUser());
        model.addAttribute("fill", fillInTheBlankService.findById(id));
        model.addAttribute("left", left);
        model.addAttribute("xpchange", xpChange);
        return "fillInTheBlank/fillInTheBlank";
    }

    @GetMapping("/kata")
    public String displayKataAssignment(@PathVariable Long id, Model model) {
        model.addAttribute("student", getLoggedInUser());
        model.addAttribute("kata", kataService.findById(id));
        return "kata/katas";
    }

    @PostMapping("/kata")
    public String submitKataAssignment(@ModelAttribute Kata kata) {
        kataService.save(kata);
        return "redirect:assignments/assignments";
    }

    @GetMapping("/pa/{id}")
    public String displayPAAssignment(@PathVariable Long id,  Model model) {
        checkForNewFriendRequest();
        model.addAttribute("student", getLoggedInUser());
        model.addAttribute("question", paService.findById(id));
        return "pa/pas";
    }

    @PostMapping("/pa")
    public String submitPAAssignment(@ModelAttribute PA pa) {
        paService.save(pa);
        return "redirect:assignments/assignments";
    }

    //TODO rewrite 5-question quiz
    @GetMapping("/question")
    public String getQuestion(@PathVariable Long numberLeft, @PathVariable Long xpChange) {

        Student student = getLoggedInUser();
        CourseType studentCoure = student.getCourse().getName();
        Level studentLevel = student.getLevel();

        List<FillInTheBlank> fillInQuestionList = fillInTheBlankService.findFillInTheBlanksByCourseTypeAndLevel(studentCoure, studentLevel);
        List<QuizQuestion> quizQuestionList = quizQuestionService.findFillInTheBlanksByCourseTypeAndLevel(studentCoure, studentLevel);

        Assignment currentAssignment = randomizeAssignment.makeRandomList(quizQuestionList, fillInQuestionList);

        if(questionsCompleted.contains(currentAssignment)){
            while(questionsCompleted.contains(currentAssignment)) {
                currentAssignment = randomizeAssignment.makeRandomList(quizQuestionList, fillInQuestionList);
            }
        }
        if(numberLeft==0){
            questionsCompleted.clear();
            randomizeAssignment.makeRandomList(quizQuestionList, fillInQuestionList);
        }

        String returnUrl = null;

        if(currentAssignment.getClass() == FillInTheBlank.class){
            long next = currentAssignment.getId();
            questionsCompleted.add(currentAssignment);
            returnUrl = "/fill?id=" + next + "&left=" + numberLeft + "&xp=" + xpChange;
        } else {
            long nextQuiz = currentAssignment.getId();
            questionsCompleted.add(currentAssignment);
            returnUrl = "/quiz?id="+nextQuiz + "&left=" + numberLeft + "&xp=" + xpChange;
        }
        return returnUrl;
    }


    @GetMapping("/quiz/{id}/{left}/{xp}")
    public String displayQuizAssignment(@PathVariable Long id, @PathVariable String left, @PathVariable Long xpChange, Model model) {
        checkForNewFriendRequest();
        model.addAttribute("student", getLoggedInUser());
        model.addAttribute("question", quizQuestionService.findById(id));
        model.addAttribute("left", left);
        model.addAttribute("xpchange", xpChange);
        return "quiz/quizzes";
    }

    //TODO is this an API?
    @PostMapping("/quiz/{xp}")
    public void setXP(@PathVariable Long xp) {
        Student student = getLoggedInUser();
        student.setXp(xp);
        studentService.save(student);
    }
}
