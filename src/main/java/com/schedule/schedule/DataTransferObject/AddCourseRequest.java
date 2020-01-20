package com.schedule.schedule.DataTransferObject;

import lombok.Data;

@Data
public class AddCourseRequest {

    private String name;
    private Integer coursesNumber;
    private Integer courseHour;
    private Integer laboratoriesNumber;
    private Integer laboratoryHour;
}
