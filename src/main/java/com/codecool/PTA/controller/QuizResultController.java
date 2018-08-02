package com.codecool.PTA.controller;

import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.service.CourseService;
import com.codecool.PTA.service.UserDetailServiceImp;
import com.codecool.PTA.service.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuizResultController extends AbstractController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserDetailServiceImp userDetailServiceImp;

    @GetMapping("/quiz-result/{xp}")
    public String showResult(Model model, @PathVariable Long xp) {
        UserDetailsImp userDetails = userDetailServiceImp.loadUserByUsername(getLoggedInUser().getUsername());
        Student student = userDetails.getUser();
        checkForNewFriendRequest();
        long xpToNextLevel = student.getLevel().getNextLevel().getEntryRequirement()-student.getXp();
        model.addAttribute("student", student);
        model.addAttribute("receivedxp", xp);
        model.addAttribute("nextlevelxp", xpToNextLevel);
        return "result/result";
    }
}
