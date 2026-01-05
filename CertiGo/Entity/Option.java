package com.codeline.CertiGo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String optionText;
    private Boolean isCorrect;
    private Boolean isActive;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "question")
    private Question question;
}
