//package com.codeline.CertiGo.Entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//@Table
//public class UserAnswer {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Integer id;
//
//    private String selectedOption;
//    private Boolean isCorrect;
//
//    @ManyToOne
//    @JoinColumn(name = "user")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "quiz")
//    private Quiz quiz;
//
//    @ManyToOne
//    @JoinColumn(name = "question")
//    private Question question;
//
//    private QuizResult quizResult;
//
//}
package com.codeline.CertiGo.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String selectedOption;
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "question")
    private Question question;

    // 🔴 REQUIRED
    @ManyToOne
    @JoinColumn(name = "quiz_result")
    private QuizResult quizResult;
}
