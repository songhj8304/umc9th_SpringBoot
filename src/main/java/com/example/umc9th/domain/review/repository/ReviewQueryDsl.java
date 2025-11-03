package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {

    //검색 API
    List<Review> searchReview(Predicate predicate);

    //내 리뷰 조회 API
    List<Review> findMyReviews(Predicate predicate);

}
