package com.codecool.PTA.api;

import com.codecool.PTA.controller.AbstractController;
import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.service.CourseService;
import com.codecool.PTA.service.StudentService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SendCourseInfo extends AbstractController {

    private final static String SUCCESS = "success";

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/courseinfo")
    public ResponseEntity<JSONObject> courseInfo(@RequestParam("id") Long id) {
        Course course = courseService.findById(id);
        Map<String, String> courseInfo = new HashMap<>() ;
        courseInfo.put("name", course.getName().toString());
        courseInfo.put("description", course.getDescription());
        JSONObject response = new JSONObject(courseInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/courseinfo")
    public ResponseEntity<String> signUpToCourse(@PathVariable("id") Long id) {
        Course course = courseService.findById(id);
        Student student = getLoggedInUser();
        student.setCourse(course);
        studentService.save(student);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

}