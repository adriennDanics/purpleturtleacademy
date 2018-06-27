package com.codecool.PTA.service;

import com.codecool.PTA.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImp implements UserDetailsService {

    private StudentService studentService;

    @Autowired
    public void userDetailsService(StudentService studentService){
        this.studentService = studentService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = studentService.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImp(user);
    }
}
