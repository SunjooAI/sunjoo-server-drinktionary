package com.sunjoo.drinktionary.dto;

import com.sunjoo.drinktionary.entity.Review;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ReviewResponses {
    private final List<ReviewResponse> reviews;

    // review 객체들의 리스트를 받아 ReviewResponses 객체를 생성하는 정적 팩토리 메서드
    public static ReviewResponses fromReviews(final List<Review> reviews) {
        final List<ReviewResponse> result = reviews.stream()
                .map(ReviewResponse::createFromReview)
                .toList();

        return new ReviewResponses(result);
    }
}
