package com.codecool.PTA.controller;

import com.codecool.PTA.helper.Hash;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.persistence.PersistenceImplementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;

abstract class ControllerTest {

    Student student;
    Hash hash;
    HttpServletRequest req;
    HttpServletResponse resp;
    HttpSession session;

    PersistenceImplementation pim;

    void mockClasses() {
        this.req = mock(HttpServletRequest.class);
        this.resp = mock(HttpServletResponse.class);
        this.session = mock(HttpSession.class);
        this.pim = mock(PersistenceImplementation.class);
        this.hash = mock(Hash.class);
    }

}
