package com.codecool.PTA.api;

import com.codecool.PTA.controller.AbstractController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoveNotification extends AbstractController {

    @PostMapping("/remove-notification")
    public ResponseEntity<String> removeNotification() {
        getHttpSession().removeAttribute("newRequest");
        isFirstNotification = false;
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

}
