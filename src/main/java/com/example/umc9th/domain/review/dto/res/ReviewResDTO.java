package com.example.umc9th.domain.review.dto.res;


import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record ReviewDTO(
            Long id,
            Long memberId,
            String storeName,
            Float star,
            String content
    ){}
}
