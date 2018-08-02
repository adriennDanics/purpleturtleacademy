package com.codecool.PTA.api;

import com.codecool.PTA.controller.AbstractController;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.service.PasswordUtilService;
import com.codecool.PTA.service.StudentService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EditUserInformation extends AbstractController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private PasswordUtilService passwordUtilService;

    @RequestMapping(value="/profile/newpassword", method = RequestMethod.POST)
    public ResponseEntity<?> getNewPassword(@RequestBody JSONObject passwords) {
        Student student = getLoggedInUser();
        String oldPassword = passwords.get("oldpassword").toString();
        String newPassword = passwords.get("newpassword").toString();
        boolean isPassWordCorrect = passwordUtilService.isPasswordCorrect(oldPassword, student.getPassword());

        if(isPassWordCorrect){
            student.setPassword(passwordUtilService.hashPassword(newPassword));
            studentService.save(student);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        } else {
            Map<String, String> denial = new HashMap<>();
            denial.put("message", "Old password was wrong, change not possible");
            JSONObject response = new org.json.simple.JSONObject(denial);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @RequestMapping(value="/profile/newname", method = RequestMethod.POST)
    public ResponseEntity<String> getNewName(@RequestBody JSONObject newName) {
        Student student = getLoggedInUser();
        student.setUsername(newName.get("username").toString());
        studentService.save(student);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @RequestMapping(value="/quiz-xp-mod", method = RequestMethod.POST)
    public ResponseEntity<String> setXP(@RequestBody JSONObject xp) {
        Student student = getLoggedInUser();
        student.setXp(Long.valueOf(xp.get("xp").toString()));
        studentService.save(student);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}