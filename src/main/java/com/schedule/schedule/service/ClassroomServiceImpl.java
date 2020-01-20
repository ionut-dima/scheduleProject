package com.schedule.schedule.service;

import com.schedule.schedule.DataTransferObject.AddClassroomRequest;
import com.schedule.schedule.Entity.Classroom;
import com.schedule.schedule.repository.ClassroomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClassroomServiceImpl implements ClassroomService{

    @Autowired
    ClassroomRepository classroomRepository;

    @Override
    public List<Classroom> getAllClassroom() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom createClassroom(AddClassroomRequest addClassroomRequest){

        Classroom classroom = new Classroom(addClassroomRequest.getName(), addClassroomRequest.getType());

        return classroomRepository.save(classroom);
    }
}
