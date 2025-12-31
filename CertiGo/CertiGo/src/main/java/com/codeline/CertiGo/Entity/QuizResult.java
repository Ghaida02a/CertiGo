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
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer quizId;
    private Integer score;
    private Boolean isPassed;
    private Boolean isActive;
    private Date createdAt;
    private Date updatedAt;
}
