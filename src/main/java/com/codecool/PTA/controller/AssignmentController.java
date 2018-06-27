package com.codecool.PTA.controller;

import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.Kata;
import com.codecool.PTA.model.quest.PA;
import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//TODO
@Controller
public class AssignmentController extends AbstractController {

    @Autowired
    private KataService kataService;

    @Autowired
    private PaService paService;

    @Autowired
    private FillInTheBlankService fillInTheBlankService;

    @Autowired
    private QuizQuestionService quizQuestionService;

    @Autowired
    private StudentService studentService;


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

    @GetMapping("/fill/{id}/{left}")
    public String displayFillAssignment(@PathVariable Long id, @PathVariable String left, Model model) {
        checkForNewFriendRequest();
        model.addAttribute("student", getLoggedInUser());
        model.addAttribute("fill", fillInTheBlankService.findById(id));
        model.addAttribute("left", left);
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
        kataService.update(kata);
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
        paService.update(pa);
        return "redirect:assignments/assignments";
    }

    //TODO rewrite 5-question quiz
    //@GetMapping("/question")

    @GetMapping("/quiz/{id}/{left}")
    public String displayQuizAssignment(@PathVariable Long id, @PathVariable String left, Model model) {
        checkForNewFriendRequest();
        model.addAttribute("student", getLoggedInUser());
        model.addAttribute("question", quizQuestionService.findById(id));
        model.addAttribute("left", left);
        return "quiz/quizzes";
    }

    //TODO is this an API?
    @PostMapping("/quiz/{xp}")
    public void setXP(@PathVariable Long xp) {
        Student student = getLoggedInUser();
        student.setXp(xp);
        studentService.saveStudent(student);
    }
}
