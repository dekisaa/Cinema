package com.project.cinema.repository;

import com.project.cinema.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HallRepository extends JpaRepository<Hall, Long> {
    Optional<Hall> findByMark(String mark);
}
