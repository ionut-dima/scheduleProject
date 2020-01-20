package com.schedule.schedule.service;

import com.schedule.schedule.DataTransferObject.AddCourseRequest;
import com.schedule.schedule.Entity.Course;
import com.schedule.schedule.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course createCourse(AddCourseRequest addCourseRequest){

        Course course = new Course(addCourseRequest.getName(), addCourseRequest.getCoursesNumber(), addCourseRequest.getCourseHour(),
                addCourseRequest.getLaboratoriesNumber(), addCourseRequest.getLaboratoryHour());

        return courseRepository.save(course);
    }
}
