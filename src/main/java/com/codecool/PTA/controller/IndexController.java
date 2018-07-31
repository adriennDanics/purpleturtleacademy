package com.codecool.PTA.controller;

import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends AbstractController {

    private final static long ORIENTATION = 3L;

    @Autowired
    private CourseService courseService;

    @GetMapping({"", "index"})
    public String displayIndexPage(Model model, HttpServletRequest request) {
//        checkForNewFriendRequest();
        Student student = getLoggedInUser();
        Course course = student.getCourse() == null ? courseService.findById(ORIENTATION) : student.getCourse();
        boolean IS_ORIENTATION_ACTIVE = course.getName() == CourseType.ORIENTATION;
        if (IS_ORIENTATION_ACTIVE) {
            model.addAttribute("orientation", "orientation");
        }
        model.addAttribute("student", student);
        model.addAttribute("course", course);
        return "index/index";
    }

}
