package com.schedule.schedule.repository;

import com.schedule.schedule.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByNameAndPassword(String name, String password);

    List<User> findByIsTeacher(Boolean isTeacher);
}
