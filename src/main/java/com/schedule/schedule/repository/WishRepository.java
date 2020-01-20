package com.schedule.schedule.repository;

import com.schedule.schedule.Entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish, Integer> {

    List<Wish> findByDayId(Integer dayId);

    List<Wish> findAll();
}
