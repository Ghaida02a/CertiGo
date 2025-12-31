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
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String questionText;
    private List<String> options;
    private String correctAnswer;
    private Boolean isActive;
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "quiz")
    private Quiz quiz;
}
