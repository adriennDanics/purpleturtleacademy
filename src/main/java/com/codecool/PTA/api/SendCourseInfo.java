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

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/courseinfo")
    public ResponseEntity<JSONObject> getCourseInfo(@RequestParam("id") Long id) {
        Map<String, String> courses = new HashMap<>();
        Course course = courseService.findById(id);
        courses.put("name", course.getName().toString());
        courses.put("description", course.getDescription());
        JSONObject response = new JSONObject(courses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/courseinfo")
    public ResponseEntity<String> signUpToCourse(@RequestParam("id") Long id) {
        Student student = getLoggedInUser();
        Course course = courseService.findById(id);
        student.setCourse(course);
        studentService.save(student);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}