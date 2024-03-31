package com.sumit.jobappy.reviews.impl;

import com.sumit.jobappy.company.Company;
import com.sumit.jobappy.company.CompanyService;
import com.sumit.jobappy.reviews.Review;
import com.sumit.jobappy.reviews.ReviewRepository;
import com.sumit.jobappy.reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService  companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviewList = reviewRepository.findByCompanyId(companyId);
        return reviewList;
    }

    @Override
    public Boolean addCompanyReview(Long companyId, Review reviewCompany) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null){
            reviewCompany.setCompany(company);
            reviewRepository.save(reviewCompany);
            return true;
        }else
        return false;
    }

    @Override
    public Review getCompanyReviewById(Long companyId ,Long reviewId) {
           List<Review> reviewList = reviewRepository.findByCompanyId(companyId);
        return reviewList.stream()
                         .filter(review -> review.getId().equals(reviewId))
                         .findFirst()
                         .orElse(null);
    }

    @Override
    public Boolean updateCompanyReview(Long companyId, Long reviewId, Review updatedReview) {
        if (reviewRepository.findByCompanyId(companyId) != null){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }else
        return false;
    }

    @Override
    public Boolean deleteCompanyReview(Long companyId, Long reviewId) {
         if (companyService.getCompanyById(companyId)!= null &&
                    reviewRepository.existsById(reviewId)){
                Review review = reviewRepository.findById(reviewId).orElse(null);
                Company company = review.getCompany();
                company.getReviews().remove(review);
                companyService.updateByCompanyId(companyId,company);
                reviewRepository.deleteById(reviewId);
             return true;
         }

        return false;
    }

}
