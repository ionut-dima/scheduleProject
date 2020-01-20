package com.schedule.schedule.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @NotBlank
    private String name;

    private Integer coursesNumber;
    private Integer courseHour;
    private Integer laboratoriesNumber;
    private Integer laboratoryHour;

    @ManyToMany
    @JoinTable(
            name = "user_and_course",
            joinColumns = @JoinColumn(name="course_id", referencedColumnName="courseId"),
            inverseJoinColumns = @JoinColumn(name="user_id", referencedColumnName="userId")
    )
    private List<User> teachers = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "wishes_and_course",
            joinColumns = @JoinColumn(name="course_id", referencedColumnName="courseId"),
            inverseJoinColumns = @JoinColumn(name="wish_id", referencedColumnName="wishId")
    )
    private List<TeacherWishes> teacherWishes = new ArrayList<>();

    public Course(String name, Integer coursesNumber, Integer courseHour, Integer laboratoriesNumber, Integer laboratoryHour) {
        this.name = name;
        this.coursesNumber = coursesNumber;
        this.courseHour = courseHour;
        this.laboratoriesNumber = laboratoriesNumber;
        this.laboratoryHour = laboratoryHour;
    }
}
