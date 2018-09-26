package com.codecool.PTA.controller;

import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuizResultController extends AbstractController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/quiz-result")
    public String showResult(Model model, @PathVariable Long xp) {
        Student student = getLoggedInUser();
        checkForNewFriendRequest();
        long xpToNextLevel = student.getLevel().getNextLevel().getEntryRequirement()-student.getXp();
        model.addAttribute("student", student);
        model.addAttribute("receivedxp", xp);
        model.addAttribute("nextlevelxp", xpToNextLevel);
        return "result/result";
    }
}
