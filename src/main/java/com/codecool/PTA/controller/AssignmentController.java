package com.codecool.PTA.controller;

import com.codecool.PTA.helper.RandomizeAssignment;
import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.*;
import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO
@Controller
@SessionAttributes({"student"})
public class AssignmentController extends AbstractController {

    @Autowired
    private KataService kataService;

    @Autowired
    private KataSolutionService kataSolutionService;

    @Autowired
    private PASolutionService paSolutionService;

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
        Kata kata = kataService.findById(id);
        Student student = getLoggedInUser();
        model.addAttribute("student", student);
        model.addAttribute("kata", kata);
        model.addAttribute("kataSolution", new KataSolution(kata, student));
        return "kata/katas";
    }

    @PostMapping("/kata/{id}")
    public String submitKataAssignment(@PathVariable Long id, @RequestParam("submission") String solution) {
        Student student = getLoggedInUser();
        Kata kata = kataService.findById(id);
        KataSolution kataSolution = new KataSolution();
        kataSolution.setSolution(solution);
        kataSolution.setKata(kata);
        kataSolution.setStudent(student);
        kataSolutionService.save(kataSolution);
        return "redirect:/assignments";
    }

    @GetMapping("/pa/{id}")
    public String displayPAAssignment(@PathVariable Long id, Model model) {
        PA pa = paService.findById(id);
        Student student = getLoggedInUser();
        model.addAttribute("student", student);
        model.addAttribute("pa", pa);
        model.addAttribute("paSolution", new PASolution(pa, student));
        return "pa/pas";
    }

    @PostMapping("/pa/{id}")
    public String submitPAAssignment(@PathVariable Long id, @RequestParam("submission") String solution) {
        Student student = getLoggedInUser();
        PA pa = paService.findById(id);
        PASolution paSolution = new PASolution();
        paSolution.setSolution(solution);
        paSolution.setPA(pa);
        paSolution.setStudent(student);
        paSolutionService.save(paSolution);
        return "redirect:/assignments";
    }

    @GetMapping("/quiz-start")
    public String getQuiz(){
        randomAssignment = randomizeAssignment.makeRandomList(getStudent());
        return "redirect:/question/4/0";
    }

    @GetMapping("/question/{numberLeft}/{xpChange}")
    public String getQuestion(@PathVariable Integer numberLeft, @PathVariable Long xpChange) {
//        for(Assignment assignment: randomAssignment){
//            System.out.println(assignment.toString());
//        }
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
