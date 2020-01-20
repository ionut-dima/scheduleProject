package com.schedule.schedule.logic;

import com.schedule.schedule.Entity.Wish;

import java.util.List;

public interface AiLogic {

   List<Wish> resolveConstraints();
}
