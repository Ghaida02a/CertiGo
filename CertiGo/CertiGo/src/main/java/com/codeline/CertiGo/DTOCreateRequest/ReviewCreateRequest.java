package com.codeline.CertiGo.DTOCreateRequest;

import com.codeline.CertiGo.Entity.Review;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateRequest {

    private String reviewerName;
    private String comments;
    private Integer rating;
    private Integer courseId;
    private Integer userId;


    public static Review convertToReview(ReviewCreateRequest request) {
        Review review = new Review();
        if (request != null) {
            review.setReviewerName(request.getReviewerName());
            review.setComments(request.getComments());
            review.setRating(request.getRating());
        }
        return review;
    }

    public static void validCreateReviewRequest(ReviewCreateRequest request) throws CustomException {
        if (Utils.isNull(request.getReviewerName()) || request.getReviewerName().isBlank() || request.getReviewerName().isEmpty()) {
            throw new CustomException(Constants.REVIEW_CREATE_REQUEST_NAME_NOT_VALID , Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getComments()) || request.getComments().isBlank()) {
            throw new CustomException(Constants.REVIEW_CREATE_REQUEST_COMMENTS_NOT_VALID , Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getRating()) || request.getRating() <= 0) {
            throw new CustomException(Constants.REVIEW_CREATE_REQUEST_RATING_NOT_VALID , Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNull(request.getCourseId()) || request.getCourseId() <= 0) {
            throw new CustomException(Constants.REVIEW_CREATE_REQUEST_COURSE_ID_NOT_VALID , Constants.HTTP_STATUS_IS_NULL);
        } else if (Utils.isNotNull(request.getUserId()) || request.getUserId() <= 0) {
            throw new CustomException(Constants.REVIEW_CREATE_REQUEST_USER_ID_NOT_VALID , Constants.HTTP_STATUS_IS_NULL);
        }
    }
}
