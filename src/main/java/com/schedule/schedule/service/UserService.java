package com.schedule.schedule.service;

import com.schedule.schedule.DataTransferObject.AddUserRequest;
import com.schedule.schedule.Entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllTeacher();
    User createUser(AddUserRequest addUserRequest);
}
