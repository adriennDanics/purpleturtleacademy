package com.codecool.PTA.repository;

import com.codecool.PTA.model.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
