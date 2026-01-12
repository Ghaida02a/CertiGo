package com.codeline.CertiGo.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String courseName;
    private String type;
    private Integer durationHours;
    private Double price;
    private Boolean isCompleted;
    private Boolean isActive;
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

    @ManyToMany
    @JoinTable(
            name = "course_instructor",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id")
    )
    private List<Instructor> instructors;
}
