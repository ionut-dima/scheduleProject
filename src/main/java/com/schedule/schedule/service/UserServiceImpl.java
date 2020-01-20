package com.schedule.schedule.service;

import com.schedule.schedule.DataTransferObject.AddUserRequest;
import com.schedule.schedule.Entity.User;
import com.schedule.schedule.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllTeacher() {
        return userRepository.findByIsTeacher(Boolean.TRUE);
    }

    @Override
    public User createUser(AddUserRequest addUserRequest) {

        User user = new User(addUserRequest.getName(), addUserRequest.getPassword(), addUserRequest.getGrade());
        user.setIsTeacher(Boolean.TRUE);
         return userRepository.save(user);
}
}
