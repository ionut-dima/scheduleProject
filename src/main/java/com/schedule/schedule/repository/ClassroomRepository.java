package com.schedule.schedule.repository;

import com.schedule.schedule.Entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository  extends JpaRepository<Classroom, Integer> {

    List<Classroom> findAll();
}
