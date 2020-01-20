package com.schedule.schedule.service;

import com.schedule.schedule.Entity.Wish;

import java.util.List;

public interface WishService {

    List<Wish> getAllWishesByDayId(Integer dayId);

    List<Wish> getAllWishes();
}
