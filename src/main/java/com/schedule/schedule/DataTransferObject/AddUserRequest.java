package com.schedule.schedule.DataTransferObject;

import lombok.Data;

@Data
public class AddUserRequest {

    private String name;
    private String password;
    private String grade;
    private Boolean isTeacher = Boolean.TRUE;
}
