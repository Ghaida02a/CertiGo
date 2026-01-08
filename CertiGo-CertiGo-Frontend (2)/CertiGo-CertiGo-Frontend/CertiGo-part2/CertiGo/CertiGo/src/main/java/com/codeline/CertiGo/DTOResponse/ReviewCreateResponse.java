package com.codeline.CertiGo.DTOResponse;

import com.codeline.CertiGo.Entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateResponse {
    private Integer id;
    private String reviewerName;
    private String comments;
    private Integer rating;

    public static ReviewCreateResponse convertToReviewResponse(Review entity) {
        ReviewCreateResponse response = ReviewCreateResponse.builder()
                .id(entity.getId())
                .reviewerName(entity.getReviewerName())
                .comments(entity.getComments())
                .rating(entity.getRating())
                .build();
        return response;
    }
}
