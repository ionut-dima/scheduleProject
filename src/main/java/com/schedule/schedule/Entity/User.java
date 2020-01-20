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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    private Boolean isAdmin;
    private Boolean isTeacher = Boolean.TRUE;
    private String grade;

    @ManyToMany
    @JoinTable(
            name = "user_and_course",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName="userId"),
            inverseJoinColumns = @JoinColumn(name="course_id", referencedColumnName="courseId")
    )
    private List<Course> courses = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "wishes_and_user",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName="userId"),
            inverseJoinColumns = @JoinColumn(name="wish_id", referencedColumnName="wishId")
    )
    private List<TeacherWishes> teacherWishes  = new ArrayList<>();

    public User(String name, String password, String grade) {
        this.name = name;
        this.password = password;
        this.grade = grade;
    }
}
