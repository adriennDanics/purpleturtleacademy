package com.codecool.PTA.repository;

import com.codecool.PTA.model.quest.Kata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KataRepository extends JpaRepository<Kata, Long> {
}
