package com.schedule.schedule.service;

import com.schedule.schedule.Entity.Wish;
import com.schedule.schedule.repository.WishRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WishServiceImpl implements WishService {

    @Autowired
    WishRepository wishRepository;
    public List<Wish> getAllWishesByDayId(Integer dayId) {

        return wishRepository.findByDayId(dayId);
    }

    public List<Wish> getAllWishes() {

        return wishRepository.findAll();
    }

}
