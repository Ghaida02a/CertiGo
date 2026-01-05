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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public ReviewCreateResponse saveReview(ReviewCreateRequest reviewDTO) throws CustomException {
        Review review = ReviewCreateRequest.convertToReview(reviewDTO);

        Course course = courseRepository.findById(reviewDTO.getCourseId()).get();
        if(Utils.isNotNull(course)){
            review.setCourse(course);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_NOT_FOUND);
        }
        User user = userRepository.findById(reviewDTO.getUserId()).get();
        if(Utils.isNotNull(user)){
            review.setUser(user);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_NOT_FOUND);
        }

        review.setCreatedAt(new Date());
        review.setIsActive(Boolean.TRUE);
        return ReviewCreateResponse.convertToReviewResponse(reviewRepository.save(review));
    }

    public Review updateReview(Review review) throws CustomException {
        Review existingReview = reviewRepository.findById(review.getId()).get();
        if (existingReview != null && existingReview.getIsActive()) {
            review.setUpdatedAt(new Date());
            return reviewRepository.save(review);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public void deleteReview(Integer id) throws CustomException {
        Review existingReview = reviewRepository.findById(id).get();
        if (existingReview != null && existingReview.getIsActive()) {
            existingReview.setUpdatedAt(new Date());
            existingReview.setIsActive(Boolean.FALSE);
            reviewRepository.save(existingReview);
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    public Review getReviewByCourseId(Integer id) throws CustomException{
        Review existingReview = reviewRepository.findById(id).get();
        if (existingReview != null && existingReview.getIsActive()){
            return existingReview;
        } else {
            throw new CustomException(Constants.BAD_REQUEST,Constants.HTTP_STATUS_BAD_REQUEST);
        }
    }
}
