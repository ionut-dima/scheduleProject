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
public class Conflict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer conflictId;

    private Integer firstUserId;
    private Integer firstStartInterval;
    private Integer firstEndInterval;
    private Integer secondUserId;
    private Integer secondStartInterval;
    private Integer secondEndInterval;
    private Integer classroomId;
    private Integer dayId;

    @OneToMany(mappedBy = "conflict")
    private List<Comment> comments = new ArrayList<>();
}
