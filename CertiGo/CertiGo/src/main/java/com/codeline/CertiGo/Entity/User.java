package com.codeline.CertiGo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String role;
    private Boolean isActive;
    private Date createdAt;
    private Date updatedAt;

    @ManyToMany(mappedBy = "courses")
    private List<Course> courses;

    @ManyToMany(mappedBy = "jobs")
    private List<Job> jobs;
}
