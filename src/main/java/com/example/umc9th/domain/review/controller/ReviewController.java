package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.query.ReviewQueryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryServiceImpl reviewQueryService;
    private final ReviewConverter reviewConverter;

    //리뷰 검색
    @GetMapping("/reviews/search")
    public List<ReviewResDTO.ReviewDTO> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ){

        List<Review> reviews = reviewQueryService.searchReview(query, type);
        return reviews.stream()
                .map(reviewConverter::toReviewDTO)
                .collect(Collectors.toList());
    }

    //나의 리뷰 조회
    @GetMapping("/reviews/my")
    public List<ReviewResDTO.ReviewDTO> findMyReview(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer starRange
    ){

        List<Review> reviews = reviewQueryService.findMyReviews(memberId, storeName, starRange);
        return reviews.stream()
                .map(reviewConverter::toReviewDTO)
                .collect(Collectors.toList());
    }
}
