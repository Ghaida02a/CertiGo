package com.codeline.CertiGo.DTOUpdateRequest;

import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Entity.Review;
import com.codeline.CertiGo.Entity.User;
import com.codeline.CertiGo.Entity.UserAnswer;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
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
public class ReviewUpdateRequest {
    private Integer id;
    private String reviewerName;
    private String comments;
    private Integer rating;
    private Course course;
    private User user;

    public static void validateReviewUpdateRequested(ReviewUpdateRequest dto) throws CustomException {
        if (Utils.isNull(dto.getId())) {
            throw new CustomException(Constants.REVIEW_CREATE_REQUEST_USER_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getReviewerName())) {
            throw new CustomException(Constants.REVIEW_CREATE_REQUEST_NAME_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getComments())) {
            throw new CustomException(Constants.REVIEW_CREATE_REQUEST_COMMENTS_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getRating())) {
            throw new CustomException(Constants.REVIEW_CREATE_REQUEST_RATING_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
        if (Utils.isNull(dto.getCourse())) {
            throw new CustomException(Constants.REVIEW_CREATE_REQUEST_COURSE_ID_NOT_VALID, Constants.HTTP_STATUS_IS_NULL);
        }
    }
    public static Review convertDTOToEntity(ReviewUpdateRequest dto) {
        Review review = Review.builder()
                .id(dto.getId())
                .reviewerName(dto.getReviewerName())
                .comments(dto.getComments())
                .rating(dto.getRating())
                .isActive(Boolean.TRUE)
                .createdAt(new Date())
                .updatedAt(new Date())
                .course(dto.getCourse())
                .user(dto.getUser())
                .build();
        return review;
    }
    public static ReviewUpdateRequest convertEntityToDTO(Review entity) {
        return ReviewUpdateRequest.builder()
                .id(entity.getId())
                .reviewerName(entity.getReviewerName())
                .comments(entity.getComments())
                .rating(entity.getRating())
                .course(entity.getCourse())
                .user(entity.getUser())
                .build();
    }
}
