package com.codecool.PTA.controller;

import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoursesController extends AbstractController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public String listCourses(Model model) {
        checkForNewFriendRequest();
        Student student = getLoggedInUser();
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("student", student);
        return "courses/courses";
    }

}
