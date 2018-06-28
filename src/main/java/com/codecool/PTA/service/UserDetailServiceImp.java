package com.codecool.PTA.service;

import com.codecool.PTA.model.role.Role;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.repository.RoleRepository;
import com.codecool.PTA.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImp implements UserDetailsService {

    private StudentService studentService;
    private StudentRepository studentRepository;
    private RoleRepository roleRepository;
    private final String USER_ROLE = "USER";

    @Autowired
    public void userDetailsService(StudentService studentService){
        this.studentService = studentService;
    }

    @Autowired
    public void roleGoat(RoleRepository roleRepository){ this.roleRepository = roleRepository; }

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository){ this.studentRepository = studentRepository; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student user = studentService.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImp(user);
    }

    public String registerUser(Student student){
        Student userCheck = studentRepository.findByUsername(student.getUsername());

        if (userCheck != null)
            return "alreadyExists";

        Role userRole = roleRepository.findByRole(USER_ROLE);
        if (userRole != null) {
            student.getRoles().add(userRole);
        } else {
            student.getRoles().add(new Role(USER_ROLE));
        }
        return null;
    }
}
