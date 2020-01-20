package com.schedule.schedule.api;

import com.schedule.schedule.DataTransferObject.AddUserRequest;
import com.schedule.schedule.DataTransferObject.UserResponse;
import com.schedule.schedule.Entity.User;
import com.schedule.schedule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private static final String API_NAME = "/admin";


    @PostMapping(API_NAME + "/addUser")
    private UserResponse addUser(@RequestBody AddUserRequest addUserRequest) {

        User user = userService.createUser(addUserRequest);
        UserResponse addUserResponse = new UserResponse();

        if (user != null) {

            addUserResponse.setUserId(user.getUserId());
            addUserResponse.setMessage("Add new user with success!");
        } else {

            addUserResponse.setUserId(null);
            addUserResponse.setMessage("Failed");
        }

        return addUserResponse;
    }

    @GetMapping(API_NAME + "/getAllTeacher")
    private List<User> getAlTeacher() {
        return userService.getAllTeacher();
    }
}
