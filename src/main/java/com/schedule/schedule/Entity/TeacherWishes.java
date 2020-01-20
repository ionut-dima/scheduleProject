package com.schedule.schedule.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherWishes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wishId;

    @ManyToMany
    @JoinTable(
            name = "wishes_and_user",
            joinColumns = @JoinColumn(name="wish_id", referencedColumnName="wishId"),
            inverseJoinColumns = @JoinColumn(name="user_id", referencedColumnName="userId")
    )
    private List<User> users = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "wishes_and_classroom",
            joinColumns = @JoinColumn(name="wish_id", referencedColumnName="wishId"),
            inverseJoinColumns = @JoinColumn(name="classroom_id", referencedColumnName="classroomId")
    )
    private List<Classroom> classrooms = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "wishes_and_course",
            joinColumns = @JoinColumn(name="wish_id", referencedColumnName="wishId"),
            inverseJoinColumns = @JoinColumn(name="course_id", referencedColumnName="courseId")
    )
    private List<Course> courses = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "wishes_and_day",
            joinColumns = @JoinColumn(name="wish_id", referencedColumnName="wishId"),
            inverseJoinColumns = @JoinColumn(name="day_id", referencedColumnName="dayId")
    )
    private List<IsOccupiedBy> dayList = new ArrayList<>();

    private Integer startInterval;
    private Integer endInterval;
    private String constraintHardSoft;

}
