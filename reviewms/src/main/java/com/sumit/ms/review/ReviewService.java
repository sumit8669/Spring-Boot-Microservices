package com.sumit.ms.review;


import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);

    Boolean addCompanyReview(Long companyId, Review reviewCompany);

    Review getReview(Long reviewId);

    Boolean updateCompanyReview(Long reviewId, Review updatedReview);

    Boolean deleteCompanyReview(Long reviewId);
}
