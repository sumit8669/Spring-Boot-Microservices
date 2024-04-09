package com.sumit.ms.review.impl;


import com.sumit.ms.review.Review;
import com.sumit.ms.review.ReviewRepository;
import com.sumit.ms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviewList = reviewRepository.findByCompanyId(companyId);
        return reviewList;
    }

    @Override
    public Boolean addCompanyReview(Long companyId,Review review) {
        if (companyId != null && review != null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }else
        return false;
    }

    @Override
    public Review getReview(Long reviewId) {
            return reviewRepository.findById(reviewId).orElse(null);
    }


    @Override
    public Boolean updateCompanyReview(Long reviewId, Review updatedReview) {
       Review review = reviewRepository.findById(reviewId).orElse(null);
            if (review != null){
                review.setTitle(updatedReview.getTitle());
                review.setDescription(updatedReview.getDescription());
                review.setRating(updatedReview.getRating());
                review.setCompanyId(updatedReview.getCompanyId());
                reviewRepository.save(review);
                return true;
            }else
        return false;
    }

    @Override
    public Boolean deleteCompanyReview(Long reviewId) {
        Review  review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null){
            reviewRepository.deleteById(reviewId);
            return true;
        }else
            return false;
    }

}
