package com.codecool.PTA.service;

import com.codecool.PTA.model.certificate.Certificate;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.repository.CertificateRepository;
import com.codecool.PTA.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CertificateRepository certificateRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findByUsername(String username){
        return studentRepository.findByUsername(username);
    }

    public Student findById(Long id) {
        return studentRepository.findOne(id);
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public Certificate findCertificateByStudentId(Long id) {
        return certificateRepository.findByStudentId(id);
    }

}
