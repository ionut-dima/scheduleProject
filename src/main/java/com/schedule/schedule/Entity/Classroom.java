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
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classroomId;

    @NotBlank
    @Column(unique = true)
    private String name;
    private String type; // course/lab

    @ManyToMany
    @JoinTable(
            name = "occupied_and_classroom",
            joinColumns = @JoinColumn(name="classroom_id", referencedColumnName="classroomId"),
            inverseJoinColumns = @JoinColumn(name="day_id", referencedColumnName="dayId")
    )
    private List<IsOccupiedBy> isOccupiedByList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "wishes_and_classroom",
            joinColumns = @JoinColumn(name="classroom_id", referencedColumnName="classroomId"),
            inverseJoinColumns = @JoinColumn(name="wish_id", referencedColumnName="wishId")
    )
    private List<TeacherWishes> teacherWishes = new ArrayList<>();

    public Classroom(String name, String type) {

        this.name = name;
        this.type = type;
    }
}
