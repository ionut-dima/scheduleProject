package com.schedule.schedule.repository;

import com.schedule.schedule.Entity.Course;
import com.schedule.schedule.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findByteachers(User teachers);

    List<Course> findAll();
}
