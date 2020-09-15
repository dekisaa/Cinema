package com.project.cinema.repository;

import com.project.cinema.model.Projection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectionRepository extends JpaRepository<Projection, Long> {
}
