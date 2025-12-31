package com.codeline.CertiGo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String jobTitle;
    private String company;
    private String location;
    private String jobType;
    private Double salary;
    private Boolean isActive;
    private Date createdAt;
    private Date updatedAt;
}
