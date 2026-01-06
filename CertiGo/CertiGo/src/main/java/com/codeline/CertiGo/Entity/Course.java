//package com.codeline.CertiGo.Entity;
//
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
//public class Course {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Integer id;
//    private String courseName;
//    private String type;
//    private Integer durationHours;
//    private Double price;
//    private Boolean isCompleted;
//    private Boolean isActive;
//    private Date createdAt;
//    private Date updatedAt;
//
//    @ManyToOne
//    @JoinColumn(name = "company")
//    private Company company;
//
//    //    @ManyToMany
////    @JoinColumn(name = "instructor")
////    private List<Instructor> instructor;
//    @ManyToMany
//    @JoinTable(
//            name = "course_instructors",
//            joinColumns = @JoinColumn(name = "course_id"),
//            inverseJoinColumns = @JoinColumn(name = "instructor_id")
//    )
//    private List<Instructor> instructors;
//
//
//    @OneToMany(mappedBy = "course")
//    private List<Lesson> lessons;
//
//    @OneToMany(mappedBy = "course")
//    private List<Enrollment> enrollments;
//
//    @OneToMany(mappedBy = "course")
//    private List<Payment> payments;
//
//    @OneToMany(mappedBy = "course")
//    private List<Quiz> quizzes;
//
//    @OneToMany(mappedBy = "course")
//    private List<Certificate> certificates;
//
//    @OneToMany(mappedBy = "course")
//    private List<QuizResult> quizResults;
//}
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

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "course")
    private List<Payment> payments;

    @OneToMany(mappedBy = "course")
    private List<Quiz> quizzes;

    @OneToMany(mappedBy = "course")
    private List<Certificate> certificates;
}
