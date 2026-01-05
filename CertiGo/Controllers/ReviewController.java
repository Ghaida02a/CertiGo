package com.codeline.CertiGo.Controllers;

import com.codeline.CertiGo.DTOCreateRequest.ReviewCreateRequest;
import com.codeline.CertiGo.DTOResponse.ReviewCreateResponse;
import com.codeline.CertiGo.Entity.Review;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Review")
public class ReviewController {
        @Autowired
        ReviewService reviewService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("create")
    public ReviewCreateResponse createReview(@RequestBody ReviewCreateRequest requestObj) throws CustomException {
        ReviewCreateRequest.validCreateReviewRequest(requestObj);
        ReviewCreateResponse review = reviewService.saveReview(requestObj);
        return review;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @GetMapping("/getAll")
    public List<Review> getAllReviews(){
        List<Review> reviews= reviewService.getAllReviews();
        return reviews;
    }

    @GetMapping("/getById/{id}")
    public Review getReviewByCourseId(@PathVariable int id) throws CustomException{
        return reviewService.getReviewByCourseId(id);
    }

    @PutMapping("/update")
    public Review updateReview(@RequestBody Review updateObjFromUser) throws CustomException{
        return reviewService.updateReview(updateObjFromUser);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteReview(@PathVariable int id) throws CustomException{
        reviewService.deleteReview(id);
        return Constants.SUCCESS;
    }
}
