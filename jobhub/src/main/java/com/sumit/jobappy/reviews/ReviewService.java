package com.sumit.jobappy.reviews;


import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);

    Boolean addCompanyReview(Long companyId, Review reviewCompany);

    Review getCompanyReviewById(Long companyId,Long reviewId);

    Boolean updateCompanyReview(Long companyId, Long reviewId, Review review);

    Boolean deleteCompanyReview(Long companyId, Long reviewId);
}
