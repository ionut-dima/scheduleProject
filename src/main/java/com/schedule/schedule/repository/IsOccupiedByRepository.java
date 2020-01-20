package com.schedule.schedule.repository;

import com.schedule.schedule.Entity.IsOccupiedBy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IsOccupiedByRepository extends JpaRepository<IsOccupiedBy, Integer> {

    List<IsOccupiedBy> findAll();
}
