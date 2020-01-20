package com.schedule.schedule.service;

import com.schedule.schedule.DataTransferObject.AddClassroomRequest;
import com.schedule.schedule.Entity.Classroom;

import java.util.List;

public interface ClassroomService {

    List<Classroom> getAllClassroom();
    Classroom createClassroom(AddClassroomRequest addClassroomRequest);
}
