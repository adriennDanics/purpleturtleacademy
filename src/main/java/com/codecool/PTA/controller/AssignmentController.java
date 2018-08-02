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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

//TODO
@Controller
@SessionAttributes({"student"})
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

    @ModelAttribute("student")
    public Student getStudent() {
        return getLoggedInUser();
    }

    private List<Assignment> randomAssignment;

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

    @GetMapping("/fill/{id}/{left}/{xpChange}")
    public String displayFillAssignment(@PathVariable Long id, @PathVariable String left, @PathVariable Long xpChange, Model model) {
        checkForNewFriendRequest();
        model.addAttribute("student", getLoggedInUser());
        model.addAttribute("fill", fillInTheBlankService.findById(id));
        model.addAttribute("left", left);
        model.addAttribute("xpchange", xpChange);
        return "fillInTheBlank/fillInTheBlank";
    }

    @GetMapping("/kata/{id}")
    public String displayKataAssignment(@PathVariable Long id, Model model) {
        model.addAttribute("student", getLoggedInUser());
        model.addAttribute("kata", kataService.findById(id));
        return "kata/katas";
    }

    @PostMapping("/kata/{id}")
    public String submitKataAssignment(@PathVariable Long id, @ModelAttribute Kata kata) {
        Kata kataBaseQuestion = kataService.findById(id);
        Student student = getLoggedInUser();
        kata.setAssignmentTitle(kataBaseQuestion.getAssignmentTitle());
        kata.setLevel(kataBaseQuestion.getLevel());
        kata.setCourseType(kataBaseQuestion.getCourseType());
        kata.setQuestion(kataBaseQuestion.getQuestion());
        student.addToCompletedKatas(kata);
        kataService.save(kata);
        studentService.save(student);
        return "redirect:/assignments";
    }

    @GetMapping("/pa/{id}")
    public String displayPAAssignment(@PathVariable Long id,  Model model) {
        checkForNewFriendRequest();
        model.addAttribute("student", getLoggedInUser());
        model.addAttribute("question", paService.findById(id));
        return "pa/pas";
    }

    @PostMapping("/pa/{id}")
    public String submitPAAssignment(@PathVariable Long id, @ModelAttribute PA pa) {
        PA paBaseQuestion = paService.findById(id);
        Student student = getLoggedInUser();
        pa.setAssignmentTitle(paBaseQuestion.getAssignmentTitle());
        pa.setLevel(paBaseQuestion.getLevel());
        pa.setCourseType(paBaseQuestion.getCourseType());
        pa.setQuestion(paBaseQuestion.getQuestion());
        student.addToCompletedPAs(pa);
        paService.save(pa);
        studentService.save(student);
        return "redirect:/assignments";
    }

    @GetMapping("/quiz-start")
    public String getQuiz(){
        randomAssignment = randomizeAssignment.makeRandomList(getStudent());
        return "redirect:/question/4/0";
    }

    @GetMapping("/question/{numberLeft}/{xpChange}")
    public String getQuestion(@PathVariable Integer numberLeft, @PathVariable Long xpChange) {
        for(Assignment assignment: randomAssignment){
            System.out.println(assignment.toString());
        }
        Assignment currentAssignment = randomAssignment.get(randomAssignment.size() - (numberLeft + 1));
        if(currentAssignment.getClass() == FillInTheBlank.class){
            long next = currentAssignment.getId();
            return "redirect:/fill/" + next + "/" + numberLeft + "/" + xpChange;
        } else {
            long nextQuiz = currentAssignment.getId();
            return "redirect:/quiz/" + nextQuiz + "/" + numberLeft + "/" + xpChange;
        }
    }


    @GetMapping("/quiz/{id}/{left}/{xpChange}")
    public String displayQuizAssignment(@PathVariable Long id, @PathVariable String left, @PathVariable Long xpChange, Model model) {
        checkForNewFriendRequest();
        model.addAttribute("student", getLoggedInUser());
        model.addAttribute("question", quizQuestionService.findById(id));
        model.addAttribute("left", left);
        model.addAttribute("xpchange", xpChange);
        return "quiz/quizzes";
    }

}
