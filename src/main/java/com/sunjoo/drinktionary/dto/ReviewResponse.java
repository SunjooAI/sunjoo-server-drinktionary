package com.sunjoo.drinktionary.dto;

import com.sunjoo.drinktionary.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Getter
@Builder
@NoArgsConstructor(force = true)
public class ReviewResponse {
    private final long id;
    private final String userId;
    private final double rating;
    private final String comment;
    private final LocalDate date;
    private final long drinkId;
    private final int drinkCapacity;

    // review entity객체 -> reviewResponse로 변환
    public static ReviewResponse createFromReview(final Review review) {
        return new ReviewResponse(review.getId(),
                review.getUserId(),
                review.getStarRating(),
                review.getContent(),
                review.getCreateAt(),
                review.getDrink().getId(),
                review.getDrink().getCapacity()
                );
    }

}
