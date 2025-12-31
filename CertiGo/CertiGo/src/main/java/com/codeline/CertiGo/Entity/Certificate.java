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
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String certificateName;
    private String description;
    private Boolean isActive;
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "course")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
}
