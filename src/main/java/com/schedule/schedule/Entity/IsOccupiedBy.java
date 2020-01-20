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
public class IsOccupiedBy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dayId;

    private Integer userId;

    @NotBlank
    private String dayName;

    private Integer classroomId;

    @ManyToMany
    @JoinTable(
            name = "occupied_and_classroom",
            joinColumns = @JoinColumn(name="day_id", referencedColumnName="dayId"),
            inverseJoinColumns = @JoinColumn(name="classroom_id", referencedColumnName="classroomId")
    )
    private List<Classroom> classrooms = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "wishes_and_day",
            joinColumns = @JoinColumn(name="day_id", referencedColumnName="dayId"),
            inverseJoinColumns = @JoinColumn(name="wish_id", referencedColumnName="wishId")
    )
    private List<TeacherWishes> teacherWishes = new ArrayList<>();

    private Boolean isOccup8_10 = Boolean.FALSE;
    private Boolean isOccup10_12 = Boolean.FALSE;
    private Boolean isOccup12_14 = Boolean.FALSE;
    private Boolean isOccup14_16 = Boolean.FALSE;
    private Boolean isOccup16_18 = Boolean.FALSE;
    private Boolean isOccup18_20 = Boolean.FALSE;
}
