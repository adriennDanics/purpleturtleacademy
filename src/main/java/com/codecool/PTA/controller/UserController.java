package com.codecool.PTA.controller;

import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController extends AbstractController {

    @Autowired
    private StudentService studentService;

    @GetMapping("student/{id}")
    public String displayProfile(@PathVariable("id") Long id, Model model) {
        checkForNewFriendRequest();
        model.addAttribute("student", studentService.findById(id));

        return "profile/profile";
    }

    @PostMapping("changeImage")
    public String changeProfilePicture(@RequestParam("new-image") String newImage, @ModelAttribute Student student) {
        final boolean NO_IMAGE_PROVIDED = newImage.equals("");
        if (NO_IMAGE_PROVIDED) {
            student.reSetDefaultImage();
        }
        studentService.saveStudent(student);

        return "redirect:profile/profile";
    }

    @GetMapping("student/{id}/certificate")
    public String displayCertificate(@PathVariable("id") Long id, Model model) {
        checkForNewFriendRequest();
        model.addAttribute("student", studentService.findById(id));
        model.addAttribute("certificate", studentService.findCertificateByStudentId(id));

        return "profile/certificate";
    }

    @GetMapping("student/{id}/friends")
    public String listFriends(@PathVariable("id") Long id, Model model) {
        checkForNewFriendRequest();
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        model.addAttribute("friends", student.getFriends());

        return "friends/friends";
    }

    @GetMapping("student/{id}/friend-requests")
    public String listFriendRequests(@PathVariable("id") Long id, Model model) {
        checkForNewFriendRequest();
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        model.addAttribute("taggedBy", student.getTaggedByOthers());

        return "friends/friendRequests";
    }

    @GetMapping("student/{id}/friendable-students")
    public String listFriendableStudents(@PathVariable("id") Long id, Model model) {
        checkForNewFriendRequest();
        Student student = studentService.findById(id);

        model.addAttribute("student", student);
        model.addAttribute("studentList", getFriendableStudents(student));

        return "listStudents/listStudents";
    }

    private List<Student> getFriendableStudents(Student student) {
        List<Student> students = studentService.findAll();
        students.remove(student);
        students.removeAll(student.getPendingFriends());
        students.removeAll(student.getFriends());
        students.removeAll(student.getTaggedByOthers());

        return students;
    }

}
