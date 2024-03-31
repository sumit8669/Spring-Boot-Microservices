package com.sumit.jobappy.reviews;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private final ReviewService reviewService;



    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>( reviewService.getAllReviews(companyId),
                HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public  ResponseEntity<String> addReviews(@PathVariable Long companyId,
                                                     @RequestBody Review reviewCompany){
        boolean isReviewSaved = reviewService.addCompanyReview(companyId, reviewCompany);
            if(isReviewSaved)
                 return new ResponseEntity<>("Review Added Successfully@@", HttpStatus.CREATED);
            else
                return new ResponseEntity<>("Unable to save review!!", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/reviews/{reviewId}")
    public  ResponseEntity<Review> getCompanyReview(@PathVariable Long companyId,
                                                    @PathVariable Long reviewId){
          return new ResponseEntity<>(reviewService.getCompanyReviewById
                  (companyId,reviewId), HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateCompanyReview(@PathVariable Long companyId,
                                                      @PathVariable Long reviewId,
                                                      @RequestBody Review review  ){

         boolean isUpdatedReview = reviewService.updateCompanyReview(companyId,reviewId,review);
        if (isUpdatedReview)
            return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Review not found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteCompanyReview(@PathVariable Long companyId, @PathVariable Long reviewId ){

        boolean isDeletedReview = reviewService.deleteCompanyReview(companyId,reviewId);
        if (isDeletedReview)
            return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Review not found",HttpStatus.NOT_FOUND);
    }
}


