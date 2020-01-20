package com.schedule.schedule.api;

import com.schedule.schedule.DataTransferObject.AddCourseRequest;
import com.schedule.schedule.DataTransferObject.CourseResponse;
import com.schedule.schedule.Entity.Course;
import com.schedule.schedule.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService;


    private static final String API_NAME = "/admin";

    @PostMapping(API_NAME + "/addCourse")
    private CourseResponse addCourse(@RequestBody AddCourseRequest addCourseRequest) {

        Course course = courseService.createCourse(addCourseRequest);
        CourseResponse courseResponse = new CourseResponse();

        if (course.getCourseId() != null) {

            courseResponse.setCourseId(course.getCourseId());
            courseResponse.setMessage("Add new course with success!");
        } else {

            courseResponse.setCourseId(null);
            courseResponse.setMessage("Failed");
        }

        return courseResponse;
    }

    @GetMapping(API_NAME + "/getAllCourses")
    private List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

}
