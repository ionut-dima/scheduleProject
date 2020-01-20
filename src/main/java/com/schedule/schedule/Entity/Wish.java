package com.schedule.schedule.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wishId;

    private Integer dayId;
    private String teacherName;
    private Integer teacherId;
    private Integer courseId;
    private Integer classroomId;
    private Integer startCourseHour;
    @Column(columnDefinition = "varchar(255) default 'soft'")
    private String constraintHs;
    @Column(columnDefinition = "boolean default false")
    private Boolean isCompleted;
    @Column(columnDefinition = "boolean default false")
    private Boolean isInterval;
}
