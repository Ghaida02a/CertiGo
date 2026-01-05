package com.codeline.CertiGo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "quiz_result_id")
    private QuizResult quizResult;
}
