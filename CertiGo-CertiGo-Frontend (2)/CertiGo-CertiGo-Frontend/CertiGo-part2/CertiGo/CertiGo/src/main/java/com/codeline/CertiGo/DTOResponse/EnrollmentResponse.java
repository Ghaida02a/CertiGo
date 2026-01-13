package com.codeline.CertiGo.DTOResponse;

import com.codeline.CertiGo.Entity.Enrollment;
import com.codeline.CertiGo.Entity.User;
import com.codeline.CertiGo.Entity.Course;  // Ensure this import is present
import com.codeline.CertiGo.Enum.EnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnrollmentResponse {
    private Integer id;
    private EnrollmentStatus status;
    private User user;
    private Course course;
    private Date enrollmentDate;

    // Convert Entity → DTO
    public static EnrollmentResponse fromEntity(Enrollment enrollment) {
        return EnrollmentResponse.builder()
                .id(enrollment.getId())
                .status(enrollment.getStatus())
                .user(enrollment.getUser())
                .course(enrollment.getCourse())
                .enrollmentDate(enrollment.getCreatedAt())
                .build();
    }
}