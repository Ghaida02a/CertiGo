package com.codeline.CertiGo.DTOResponse;

import com.codeline.CertiGo.Entity.Enrollment;
import com.codeline.CertiGo.Entity.User;
import com.codeline.CertiGo.Enum.EnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnrollmentResponse {
    private Integer id;
    private EnrollmentStatus status;
    //private String username;
     private User username;
    private Integer courseId;
    // Convert Entity → DTO
    public static EnrollmentResponse fromEntity(Enrollment enrollment) {
        return EnrollmentResponse.builder()
                .id(enrollment.getId())
                .status(enrollment.getStatus())
                .username(enrollment.getUser())
                .courseId(enrollment.getCourse().getId())
                .build();
    }
}
