package com.schedule.schedule.api;

import com.schedule.schedule.DataTransferObject.AddClassroomRequest;
import com.schedule.schedule.DataTransferObject.ClassroomResponse;
import com.schedule.schedule.Entity.Classroom;
import com.schedule.schedule.repository.ClassroomRepository;
import com.schedule.schedule.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private ClassroomRepository classroomRepository;

    private static final String API_NAME = "/admin";

    @PostMapping(API_NAME + "/addClassrooms")
    private ClassroomResponse addClassroom(@RequestBody List<AddClassroomRequest> addClassroomRequests) {

        ClassroomResponse classroomResponse = new ClassroomResponse();
        List<Classroom> classroomList = new ArrayList<>();

        addClassroomRequests.forEach(addClassroomRequest -> {
            classroomList.add(classroomService.createClassroom(addClassroomRequest));
        });

        if (classroomList.size() == 0) {
            classroomResponse.setClassroomNumber(0);
            classroomResponse.setMessage("Failed!");
        } else {
            classroomResponse.setClassroomNumber(classroomList.size());
            classroomResponse.setMessage("Add new Classrooms with Success!");
        }

        return classroomResponse;
    }

    @GetMapping(API_NAME + "/getAllClassroom")
    private List<Classroom> getAllClassroom() {
        return classroomRepository.findAll();
    }
}
