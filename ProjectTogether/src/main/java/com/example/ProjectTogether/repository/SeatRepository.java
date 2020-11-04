package com.example.ProjectTogether.repository;

import com.example.ProjectTogether.model.SeatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<SeatModel, Long> {
}