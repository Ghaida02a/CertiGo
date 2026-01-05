package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.Entity.Course;
import com.codeline.CertiGo.Entity.Review;
import com.codeline.CertiGo.Entity.User;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Helper.Utils;
import com.codeline.CertiGo.Repository.ReviewRepository;
import com.codeline.CertiGo.DTOCreateRequest.ReviewCreateRequest;
import com.codeline.CertiGo.DTOResponse.ReviewCreateResponse;
import com.codeline.CertiGo.Repository.CourseRepository;
import com.codeline.CertiGo.Repository.ReviewRepository;
import com.codeline.CertiGo.DTOCreateRequest.ReviewCreateRequest;
import com.codeline.CertiGo.DTOResponse.ReviewCreateResponse;
import com.codeline.CertiGo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;


    public List<Review> getAllReviews() throws CustomException {
        List<Review> reviews = reviewRepository.findAllReviews();
        if (Utils.isListNotEmpty(reviews)) {
            return reviews;
        } else {
            throw new CustomException(Constants.REVIEW_LIST_IS_EMPTY, Constants.HTTP_STATUS_NOT_FOUND);
        }
    }

    public ReviewCreateResponse saveReview(ReviewCreateRequest reviewDTO) throws CustomException {
        ReviewCreateRequest.validCreateReviewRequest(reviewDTO);
        Review review = ReviewCreateRequest.convertToReview(reviewDTO);


        if (Utils.isNotNull(reviewDTO)) {
            Course course = courseRepository.getCourseById(reviewDTO.getCourseId());
            if (Utils.isNotNull(course)) {
                review.setCourse(course);
            } else {
                throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_NOT_FOUND);
            }

            User user = userRepository.getUserById(reviewDTO.getUserId());
            if (Utils.isNotNull(user)) {
                review.setUser(user);
            } else {
                throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_NOT_FOUND);
            }
        }
            review.setCreatedAt(new Date());
            review.setIsActive(Boolean.TRUE);
            return ReviewCreateResponse.convertToReviewResponse(reviewRepository.save(review));
    }

    //update Review
    public Review updateReview(Review review) throws CustomException {
        Review existingReview = reviewRepository.getReviewById(review.getId());
        if (Utils.isNotNull(existingReview) && existingReview.getIsActive()) {
            review.setUpdatedAt(new Date());
            return reviewRepository.save(review);
        } else {
            throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }


    //Delete Review
    public void deleteReview(Integer id) throws CustomException {
        Review existingReview = reviewRepository.getReviewById(id);
        if (Utils.isNotNull(existingReview) && existingReview.getIsActive()) {
            existingReview.setUpdatedAt(new Date());
            existingReview.setIsActive(Boolean.FALSE);
            reviewRepository.save(existingReview);
        } else {
            throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }


    //Get Review By Course ID
    public Review getReviewByCourseId(Integer id) throws CustomException {
        Review existingReview = reviewRepository.getReviewById(id);
        if (Utils.isNotNull(existingReview) && existingReview.getIsActive()) {
            return existingReview;
        } else {
            throw new CustomException(Constants.BAD_REQUEST, Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }
}
