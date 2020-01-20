package com.schedule.schedule.service;

import com.schedule.schedule.Entity.IsOccupiedBy;
import com.schedule.schedule.repository.IsOccupiedByRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IsOccupiedByServiceImpl implements IsOccupiedByService {

    @Autowired
    IsOccupiedByRepository isOccupiedByRepository;

    public List<IsOccupiedBy> getAllDayList() {

        return isOccupiedByRepository.findAll();

    }
}
