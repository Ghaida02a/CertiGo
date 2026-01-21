//package com.codeline.CertiGo.Entity;
//
//import com.codeline.CertiGo.Enum.QuestionType;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.Date;
//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//@Table
//public class Question {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Integer id;
//    private String questionText;
//    private String correctAnswer;
//    private Boolean isActive;
//    private Date createdAt;
//    private Date updatedAt;
//
//    @ManyToOne
//    @JoinColumn(name = "quiz")
//    private Quiz quiz;
//
//    @Enumerated(EnumType.STRING)
//    private QuestionType questionType;
//
//    @OneToMany(mappedBy = "question")
//    private List<Option> options;
//
//    @OneToMany(mappedBy = "question")
//    private List<UserAnswer> userAnswers;
//}
package com.codeline.CertiGo.Entity;

import com.codeline.CertiGo.DTOCreateRequest.OptionCreateRequest;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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
    private String correctAnswer;
    private Boolean isActive;
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "quiz")
    private Quiz quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Option> options;

}
