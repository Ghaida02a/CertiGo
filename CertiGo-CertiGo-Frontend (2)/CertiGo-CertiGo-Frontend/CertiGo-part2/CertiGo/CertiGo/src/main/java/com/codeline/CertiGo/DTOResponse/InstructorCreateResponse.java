package com.codeline.CertiGo.DTOResponse;

import com.codeline.CertiGo.Entity.Instructor;
import com.codeline.CertiGo.Entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstructorCreateResponse {
    private Integer id;
    private String name;
    private String bio;
    private String email;

    public static InstructorCreateResponse convertToInstructorResponse(Instructor entity) {
        InstructorCreateResponse response = InstructorCreateResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .bio(entity.getBio())
                .email(entity.getEmail())
                .build();
        return response;
    }
}
