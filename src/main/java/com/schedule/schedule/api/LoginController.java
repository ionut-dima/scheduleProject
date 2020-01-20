package com.schedule.schedule.api;

import com.schedule.schedule.DataTransferObject.LoginRequest;
import com.schedule.schedule.DataTransferObject.LoginResponse;
import com.schedule.schedule.Entity.User;
import com.schedule.schedule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    private LoginResponse login(@RequestBody LoginRequest loginRequest) {
        User user =
                userRepository.findByNameAndPassword(loginRequest.getName(), loginRequest.getPassword()).orElse(null);

        LoginResponse loginResponse = new LoginResponse();

        if (user != null) {
            loginResponse.setUserId(user.getUserId());
            loginResponse.setMessage("Success");
        } else {
            loginResponse.setUserId(null);
            loginResponse.setMessage("Failed");
        }
        return loginResponse;
    }
}
