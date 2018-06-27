package com.codecool.PTA.controller;

import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.user.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController extends AbstractController {

    @GetMapping({"", "index"})
    public String displayIndexPage(Model model) {
        checkForNewFriendRequest();
        Student student = getLoggedInUser();
        Course course = student.getCourse();
        final boolean IS_ORIENTATION_ACTIVE = course.getName() == CourseType.ORIENTATION;
        if (IS_ORIENTATION_ACTIVE) {
            model.addAttribute("orientation", "orientation");
        }
        model.addAttribute("student", student);
        model.addAttribute("course", course);
        return "index/index";
    }

}
