package com.schedule.schedule.service;

import com.schedule.schedule.DataTransferObject.AddCourseRequest;
import com.schedule.schedule.Entity.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();

    Course createCourse(AddCourseRequest addCourseRequest);
}
