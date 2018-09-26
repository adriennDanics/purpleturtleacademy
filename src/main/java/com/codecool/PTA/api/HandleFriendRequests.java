package com.codecool.PTA.api;

import com.codecool.PTA.controller.AbstractController;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HandleFriendRequests extends AbstractController {

    @Autowired
    private StudentService studentService;

    @PostMapping("save-friend-request")
    public ResponseEntity<String> saveRequest(@RequestParam("requesterId") Long requesterId, @RequestParam("requestedId") Long requestedId) {
        Student requester = studentService.findById(requesterId);
        Student requested = studentService.findById(requestedId);

        requester.addToPendingFriends(requested);
        requested.addToTaggedByOthers(requester);

        studentService.save(requester);
        studentService.save(requested);

        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PostMapping("/accept-request")
    public ResponseEntity<String> acceptRequest(@RequestParam("requesterId") Long requesterId) {
        Student loggedInStudent = getLoggedInUser();
        Student requesterStudent = studentService.findById(requesterId);

        loggedInStudent.addToFriends(requesterStudent);
        requesterStudent.addToFriends(loggedInStudent);

        requesterStudent.removeFromPendingFriends(loggedInStudent);
        loggedInStudent.removeFromTaggedByOthers(requesterStudent);

        studentService.save(loggedInStudent);
        studentService.save(requesterStudent);

        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PostMapping("/reject-request")
    public ResponseEntity<String> rejectRequest(@RequestParam("requesterId") Long requesterId) {
        Student loggedInStudent = getLoggedInUser();
        Student requesterStudent = studentService.findById(requesterId);

        loggedInStudent.removeFromTaggedByOthers(requesterStudent);
        requesterStudent.removeFromPendingFriends(loggedInStudent);

        studentService.save(loggedInStudent);
        studentService.save(requesterStudent);

        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

}
